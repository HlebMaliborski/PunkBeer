package com.devloper.squad.feature_beer.data.repository

import com.devloper.squad.feature_beer.data.datasource.NetworkDataSource
import com.devloper.squad.feature_beer.data.mapper.BeersMapper
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.output.BeerRepository

class BeerRepositoryImpl(
  private val networkDataSource: NetworkDataSource,
  private val beerMapper: BeersMapper
) : BeerRepository {
  override suspend fun getBeers(): Result<BeerDomain> {
    return Result.runCatching {
      beerMapper.map(networkDataSource.getBeers())
    }
  }

  override suspend fun getBeer(id: Int): Result<BeerDomain> {
    return Result.runCatching {
      beerMapper.map(networkDataSource.getBeer(id))
    }
  }
}