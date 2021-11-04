package com.devloper.squad.feature_beer.data.api

import com.devloper.squad.feature_beer.data.api.ApiPaths.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
  return Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
}

fun provideOkHttpClient(): OkHttpClient {
  return OkHttpClient()
    .newBuilder()
    .addInterceptor(HttpLoggingInterceptor())
    .build()
}

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)