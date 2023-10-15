package org.andiez.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.andiez.common.exception.Failure
import org.andiez.common.functional.Either
import org.andiez.common.functional.flatMap
import org.andiez.data.detail.repository.gateway.IDetailRepository
import org.andiez.domain.model.Cast
import org.andiez.domain.util.DomainDataMapper
import javax.inject.Inject

/**
 * Created by AndiezSatria on 15/10/2023.
 */

class CastUseCase @Inject constructor(private val detailRepository: IDetailRepository) {
    operator fun invoke(type: String, id: Int): Flow<Either<Failure, List<Cast>>> {
        return detailRepository.getCasts(type, id).map { either ->
            either.flatMap { data ->
                Either.Right(DomainDataMapper.mapCastResponsesToDomains(data))
            }
        }
    }
}