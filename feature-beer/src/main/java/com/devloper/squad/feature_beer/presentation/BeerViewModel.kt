package com.devloper.squad.feature_beer.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.usecase.GetBeersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BeerViewModel(private val getBeers: GetBeersUseCase) : ViewModel() {
  private val initialState: BeerViewState by lazy { BeerViewState() }
  private val _uiState: MutableStateFlow<BeerViewState> = MutableStateFlow(initialState)

  var beers = mutableStateOf(BeerViewState())
    private set

  init {
    viewModelScope.launch {
      getBeers().onSuccess { item ->
        beers.value = BeerViewState(item)
      }
    }
  }

  data class BeerViewState(
    val beerDomain: BeerDomain = BeerDomain()
  )
}