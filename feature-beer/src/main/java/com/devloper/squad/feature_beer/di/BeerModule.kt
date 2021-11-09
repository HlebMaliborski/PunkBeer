package com.devloper.squad.feature_beer.di

import com.devloper.squad.feature_beer.data.api.provideApiService
import com.devloper.squad.feature_beer.data.api.provideOkHttpClient
import com.devloper.squad.feature_beer.data.api.provideRetrofit
import com.devloper.squad.feature_beer.data.datasource.NetworkDataSource
import com.devloper.squad.feature_beer.data.datasource.NetworkDataSourceImpl
import com.devloper.squad.feature_beer.data.mapper.BeerMapper
import com.devloper.squad.feature_beer.data.mapper.BeerMapperImpl
import com.devloper.squad.feature_beer.data.repository.BeerRepositoryImpl
import com.devloper.squad.feature_beer.domain.output.BeerRepository
import com.devloper.squad.feature_beer.domain.usecase.GetBeersUseCase
import com.devloper.squad.feature_beer.presentation.BeerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val BeerModule = module {
  single { provideOkHttpClient() }
  single { provideRetrofit(get()) }
  single { provideApiService(get()) }

  single<NetworkDataSource> { NetworkDataSourceImpl(apiService = get()) }
  single<BeerMapper> { BeerMapperImpl() }
  single<BeerRepository> { BeerRepositoryImpl(networkDataSource = get(), beerMapper = get()) }

  factory { GetBeersUseCase(beerRepository = get()) }
  viewModel { BeerViewModel(getBeers = get()) }
}