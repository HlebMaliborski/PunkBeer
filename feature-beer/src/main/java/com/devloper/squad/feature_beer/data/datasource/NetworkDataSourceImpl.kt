package com.devloper.squad.feature_beer.data.datasource

import com.devloper.squad.feature_beer.data.api.ApiService
import com.devloper.squad.feature_beer.data.model.Beer

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {
  override suspend fun getBeers(): Beer {
    return apiService.getBeers()
  }
}

interface NetworkDataSource {
  suspend fun getBeers(): Beer
}
