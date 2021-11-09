package com.devloper.squad.punkbeer

import android.app.Application
import com.devloper.squad.feature_beer.di.BeerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApp : Application() {
  private val startupModules = listOf(BeerModule)

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@BeerApp)
      modules(startupModules)
    }
  }
}