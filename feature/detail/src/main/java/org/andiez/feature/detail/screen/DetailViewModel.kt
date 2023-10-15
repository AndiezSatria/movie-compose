package org.andiez.feature.detail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.andiez.common.exception.Failure
import org.andiez.common.ui.UiState
import org.andiez.common.ui.WhileUiSubscribed
import org.andiez.common.util.CommonConstant
import org.andiez.domain.usecase.CastUseCase
import org.andiez.domain.usecase.DetailMovieUseCase
import org.andiez.domain.usecase.DetailTvUseCase
import org.andiez.domain.usecase.SetFavoriteMovieUseCase
import org.andiez.domain.usecase.SetFavoriteTvShowUseCase
import org.andiez.presenter.model.CastItem
import org.andiez.presenter.model.DetailItem
import org.andiez.presenter.util.PresenterDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val detailMovieUseCase: DetailMovieUseCase,
    private val detailTvUseCase: DetailTvUseCase,
    private val favoriteMovieUseCase: SetFavoriteMovieUseCase,
    private val favoriteTvUseCase: SetFavoriteTvShowUseCase,
    private val castUseCase: CastUseCase,
) : ViewModel() {
    val id = savedStateHandle.getStateFlow(CommonConstant.ID_ARGS, -1)
    val type = savedStateHandle.getStateFlow(CommonConstant.TYPE_ARGS, "")
    private val detailShow:
            MutableStateFlow<UiState<DetailItem>> = MutableStateFlow(UiState.Loading)
    private val casts:
            MutableStateFlow<UiState<List<CastItem>>> = MutableStateFlow(UiState.Loading)

    init {
        val id = savedStateHandle[CommonConstant.ID_ARGS] ?: -1
        val type = savedStateHandle[CommonConstant.TYPE_ARGS] ?: ""
        if (type == "movie") {
            getMovieDetail(id)
        } else {
            getTvDetail(id)
        }
        getCastDetail(type, id)
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            detailMovieUseCase.invoke(id).catch {
                detailShow.value = UiState.Error(it.message.toString())
            }.collect { result ->
                result.fold({ failure ->
                    when (failure) {
                        is Failure.ServerError -> {
                            detailShow.value = UiState.Error(failure.message.toString())
                        }

                        else -> {
                            detailShow.value = UiState.Error("Unknown Error")
                        }
                    }
                }) { data ->
                    data.let {
                        detailShow.value =
                            UiState.Success(PresenterDataMapper.mapDetailMovieDomainToPresenter(it))
                    }
                }
            }
        }
    }

    fun getTvDetail(id: Int) {
        viewModelScope.launch {
            detailTvUseCase.invoke(id).catch {
                detailShow.value = UiState.Error(it.message.toString())
            }.collect { result ->
                result.fold({ failure ->
                    when (failure) {
                        is Failure.ServerError -> {
                            detailShow.value = UiState.Error(failure.message.toString())
                        }

                        else -> {
                            detailShow.value = UiState.Error("Unknown Error")
                        }
                    }
                }) { data ->
                    data.let {
                        detailShow.value =
                            UiState.Success(PresenterDataMapper.mapDetailTvShowDomainToPresenter(it))
                    }
                }
            }
        }
    }

    fun getCastDetail(type: String, id: Int) {
        viewModelScope.launch {
            castUseCase.invoke(type, id).catch {
                casts.value = UiState.Error(it.message.toString())
            }.collect { result ->
                result.fold({ failure ->
                    when (failure) {
                        is Failure.ServerError -> {
                            casts.value = UiState.Error(failure.message.toString())
                        }

                        else -> {
                            casts.value = UiState.Error("Unknown Error")
                        }
                    }
                }) { data ->
                    data.let {
                        casts.value =
                            UiState.Success(PresenterDataMapper.mapCastDomainsToPresenters(it))
                    }
                }
            }
        }
    }

    val uiState: StateFlow<DetailUiState>
        get() = combine(
            detailShow,
            casts
        ) { showDetailUiState, castsUiState ->
            DetailUiState(showDetailUiState, castsUiState)
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = DetailUiState(
                UiState.Loading,
                UiState.Loading,
            ),
        )
}