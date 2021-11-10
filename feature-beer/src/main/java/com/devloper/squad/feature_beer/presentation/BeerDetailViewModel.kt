package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.usecase.GetBeerUseCase
import kotlinx.coroutines.launch

class BeerDetailViewModel(private val getBeerUseCase: GetBeerUseCase) : ViewModel() {

  var beers = mutableStateOf(BeerViewState())
    private set

  fun onEvent(event: Event) {
    when (event) {
      is Event.OnRequest -> {
        viewModelScope.launch {
          getBeerUseCase(event.id).onSuccess { item ->
            beers.value = BeerViewState(item)
          }.onFailure {
            Log.d("Ura", it.toString())
          }
        }
      }
    }
  }

  sealed class Event {
    class OnRequest(val id: Int) : Event()
  }

  data class BeerViewState(
    val beerDomain: BeerDomain = BeerDomain()
  )
}