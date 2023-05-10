package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.usecase.GetBeerUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BeerDetailViewModel(private val getBeerUseCase: GetBeerUseCase) : ViewModel() {
    val channel = Channel<Int>()

    init {
        viewModelScope.launch {
            val a = channel.consumeEach {
                delay(5000)
                Log.d("Ura2", it.toString())
            }
        }
    }

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
            is Event.OnTest -> {
                viewModelScope.launch {
                    getBeerUseCase.test(event.id)
                }
            }
        }
    }

    fun test() {
        viewModelScope.launch {
            channel.send(1)
        }
    }

    sealed class Event {
        class OnRequest(val id: Int) : Event()
        class OnTest(val id: Int) : Event()
    }

    data class BeerViewState(
        val beerDomain: BeerDomain = BeerDomain()
    )
}