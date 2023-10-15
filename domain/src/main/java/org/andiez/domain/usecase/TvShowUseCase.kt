package org.andiez.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.andiez.data.tvshow.repository.gateway.ITvShowRepository
import org.andiez.domain.model.TvShow
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 14/10/2023.
 */


class TvShowUseCase @Inject constructor(private val tvRepository: ITvShowRepository){
    operator fun invoke(query: String = ""): Flow<PagingData<TvShow>> {
        return if (query.isEmpty()) {
            tvRepository.getTvShows().map { pagingData ->
                pagingData.map { DomainDataMapper.mapTvEntityToDomain(it) }
            }
        } else {
            tvRepository.getSearchedTvShows(query).map { pagingData ->
                pagingData.map { DomainDataMapper.mapTvEntityToDomain(it) }
            }
        }.flowOn(Dispatchers.IO)
    }
}