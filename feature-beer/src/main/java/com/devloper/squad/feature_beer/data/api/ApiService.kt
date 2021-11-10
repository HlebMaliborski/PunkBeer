package com.devloper.squad.feature_beer.data.api

import com.devloper.squad.feature_beer.data.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

  @GET(ApiPaths.BEERS)
  suspend fun getBeers(): Beer

  @GET(ApiPaths.BEER)
  suspend fun getBeer(@Path("id") id: Int): Beer
}