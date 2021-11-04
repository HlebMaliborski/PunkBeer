package com.devloper.squad.feature_beer.data.repository

import com.devloper.squad.feature_beer.data.datasource.NetworkDataSource
import com.devloper.squad.feature_beer.data.mapper.BeerMapper
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.output.BeerRepository

class BeerRepositoryImpl(
  private val networkDataSource: NetworkDataSource,
  private val beerMapper: BeerMapper
) : BeerRepository {
  override suspend fun getBeers(): Result<BeerDomain> {
    return Result.runCatching {
      beerMapper.map(networkDataSource.getBeers())
    }
  }
}