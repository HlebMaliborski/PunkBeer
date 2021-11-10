package com.devloper.squad.punkbeer

import android.app.Application
import com.devloper.squad.feature_beer.di.BeerModule
import com.devloper.squad.punkbeer.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApp : Application() {
  private val startupModules = listOf(MainModule, BeerModule)

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@BeerApp)
      modules(startupModules)
    }
  }
}