package com.devloper.squad.punkbeer.di

import com.devloper.squad.navigation.NavigationManager
import org.koin.dsl.module

val MainModule = module {
  single { NavigationManager() }
}