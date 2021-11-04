package com.devloper.squad.feature_beer.data.api

import com.devloper.squad.feature_beer.data.model.Beer
import retrofit2.http.GET

interface ApiService {

  @GET(ApiPaths.BEERS)
  suspend fun getBeers(): Beer
}