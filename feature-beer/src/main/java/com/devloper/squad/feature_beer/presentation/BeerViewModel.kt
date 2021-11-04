package com.devloper.squad.feature_beer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.feature_beer.domain.usecase.GetBeersUseCase
import kotlinx.coroutines.launch

class BeerViewModel(private val getBeers: GetBeersUseCase) : ViewModel() {

  fun doRequest() {
    viewModelScope.launch {
      val a = getBeers()
    }
  }
}