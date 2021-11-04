package com.devloper.squad.feature_beer.domain.usecase

import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.output.BeerRepository

class GetBeersUseCase(private val beerRepository: BeerRepository) {
  suspend operator fun invoke(): Result<BeerDomain> {
    return beerRepository.getBeers()
  }
}