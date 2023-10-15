package org.andiez.feature.movie.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import org.andiez.domain.usecase.MovieUseCase
import org.andiez.presenter.util.PresenterDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 14/10/2023.
 */

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
) : ViewModel() {
    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> get() = _query
    fun onValueChange(value: String) {
        _query.value = value
    }

    val movieList =  _query
        .debounce(300)
        .distinctUntilChanged()
//        .filter { it.trim().isNotEmpty() }
        .flatMapLatest { query ->
            movieUseCase.invoke(query).map { pagingData ->
                pagingData.map { PresenterDataMapper.mapMovieDomainToPresenter(it) }
            }
        }.cachedIn(viewModelScope)
}