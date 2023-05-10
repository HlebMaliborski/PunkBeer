package com.devloper.squad.feature_beer.data.datasource

import android.util.Log
import com.devloper.squad.feature_beer.data.api.ApiService
import com.devloper.squad.feature_beer.data.model.Beer
import kotlinx.coroutines.*
import java.util.*

class NetworkDataSourceImpl(private val apiService: ApiService) : NetworkDataSource {
    private val scope: CoroutineScope = CoroutineScope(Job())
    override suspend fun getBeers(): Beer {
        return apiService.getBeers()
    }

    override suspend fun getBeer(id: Int): Beer {
        Log.d("Ppp3", Thread.currentThread().name)
        var a = apiService.getBeer(id)
        delay(3000)
        return a
    }

    private val queue = LinkedList<suspend () -> Unit>()
    private var isRunning = false

    override fun enqueue(request: suspend () -> Unit) {
        queue.add(request)
        Log.d("Ppp1", Thread.currentThread().name)
        if (!isRunning) {
            isRunning = true
            runNext()
        }
    }

    private fun runNext() {
        if (queue.isNotEmpty()) {
            val request = queue.poll()
            scope.launch(Dispatchers.IO) {
                try {
                    Log.d("Ppp2", Thread.currentThread().name)
                    request?.invoke()
                } catch (e: Exception) {
                    // Handle network errors
                } finally {
                    runNext()
                }
            }
        } else {
            isRunning = false
        }
    }

    suspend fun invokeFunctionWithRetry(
        maxRetries: Int = 3,
        delayMillis: Long = 1000L,
        function: suspend () -> Unit
    ) {
        var retryCount = 0
        while (retryCount < maxRetries) {
            try {
                function()
                return
            } catch (ex: Exception) {
                retryCount++
                delay(delayMillis)
            }
        }
        println("Function failed after $maxRetries retries")
    }

}

interface NetworkDataSource {
    fun enqueue(request: suspend () -> Unit)
    suspend fun getBeers(): Beer
    suspend fun getBeer(id: Int): Beer
}
