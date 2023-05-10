package com.devloper.squad.feature_beer.domain.output

import com.devloper.squad.feature_beer.domain.model.BeerDomain

interface BeerRepository {
  suspend fun getBeers(): Result<BeerDomain>
  suspend fun getBeer(id: Int): Result<BeerDomain>
  suspend fun test(id: Int)
}