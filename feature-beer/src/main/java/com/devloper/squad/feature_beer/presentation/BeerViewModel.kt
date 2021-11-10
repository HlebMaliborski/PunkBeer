package com.devloper.squad.feature_beer.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.usecase.GetBeersUseCase
import com.devloper.squad.navigation.BeerDetailNavigation
import com.devloper.squad.navigation.NavigationManager
import kotlinx.coroutines.launch

class BeerViewModel(
  private val getBeers: GetBeersUseCase,
  private val navigationManager: NavigationManager
) : ViewModel() {
  var beers = mutableStateOf(BeerViewState())
    private set

  init {
    viewModelScope.launch {
      getBeers().onSuccess { item ->
        beers.value = BeerViewState(item)
      }
    }
  }

  fun onEvent(event: Event) {
    when (event) {
      is Event.OnNavigate -> {
        viewModelScope.launch {
          navigationManager.navigate(BeerDetailNavigation.beer(event.id))
        }
      }
    }
  }

  data class BeerViewState(
    val beerDomain: BeerDomain = BeerDomain()
  )

  sealed class Event {
    class OnNavigate(val id: Int) : Event()
  }
}
