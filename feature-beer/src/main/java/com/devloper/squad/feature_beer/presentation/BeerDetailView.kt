package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import org.koin.androidx.compose.getViewModel

@Composable
fun BeerDetailCard(
    viewModel: BeerDetailViewModel = getViewModel(),
    id: Int = 0
) {
    viewModel.onEvent(BeerDetailViewModel.Event.OnRequest(id))

    val beerDomain = viewModel.beers.value.beerDomain.toList()
    if (beerDomain.isNotEmpty()) {
        val beerItem = beerDomain[0];

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(beerItem.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(beerItem.name)
        }
    }
}
