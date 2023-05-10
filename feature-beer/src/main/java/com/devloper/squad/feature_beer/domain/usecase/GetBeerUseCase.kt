package com.devloper.squad.feature_beer.domain.usecase

import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.output.BeerRepository

class GetBeerUseCase(private val beerRepository: BeerRepository) {
    suspend operator fun invoke(id: Int): Result<BeerDomain> {
        return beerRepository.getBeer(id)
    }

    suspend fun test(id: Int) {
        beerRepository.test(id)
    }
}