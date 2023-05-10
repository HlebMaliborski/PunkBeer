package com.devloper.squad.feature_beer.data.repository

import android.util.Log
import com.devloper.squad.feature_beer.data.datasource.NetworkDataSource
import com.devloper.squad.feature_beer.data.mapper.BeersMapper
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.output.BeerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BeerRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val beerMapper: BeersMapper
) : BeerRepository {
    val requestChannel = Channel<Int>()

    val scope = CoroutineScope(Job())

    init {
        scope.launch {
            for (request in requestChannel) {
                Log.d("Jopa5", Thread.currentThread().name)
                val response = getBeer(request)
                delay(3000)
            }
        }
    }


    override suspend fun getBeers(): Result<BeerDomain> {
        return Result.runCatching {
            beerMapper.map(networkDataSource.getBeers())
        }
    }

    override suspend fun getBeer(id: Int): Result<BeerDomain> {
        var beer: BeerDomain? = null
        networkDataSource.enqueue {
            Log.d("Ppp0", Thread.currentThread().name)
            beer = beerMapper.map(networkDataSource.getBeer(id))
        }
        return Result.runCatching {
            beer!!
        }
    }

    override suspend fun test(id: Int) {
        Log.d("Jopa1", Thread.currentThread().name)

        requestChannel.send(id)
    }
}