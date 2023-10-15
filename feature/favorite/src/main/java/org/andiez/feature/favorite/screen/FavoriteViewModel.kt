package org.andiez.feature.favorite.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import org.andiez.domain.usecase.FavoriteMovieUseCase
import org.andiez.domain.usecase.FavoriteTvUseCase
import org.andiez.presenter.util.PresenterDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModel @Inject constructor(
    private val movieFavUseCase: FavoriteMovieUseCase,
    private val tvFavUseCase: FavoriteTvUseCase,
) : ViewModel() {
    private val _selectedTab: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> get() = _selectedTab
    fun onSelectedTabChanged(value: Int) {
        _selectedTab.value = value
    }

    val favoriteList = _selectedTab.flatMapLatest { tab ->
        if (tab == 0) {
            movieFavUseCase.invoke().map { pagingData ->
                pagingData.map { PresenterDataMapper.mapMovieDomainToPresenter(it) }
            }
        } else {
            tvFavUseCase.invoke().map { pagingData ->
                pagingData.map { PresenterDataMapper.mapTvDomainToPresenter(it) }
            }
        }
    }.cachedIn(viewModelScope)
}