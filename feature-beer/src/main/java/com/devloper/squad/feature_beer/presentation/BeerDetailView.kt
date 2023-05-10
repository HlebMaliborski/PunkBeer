package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.dynatrace.android.api.compose.dtActionName
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun BeerDetailCard(
    viewModel: BeerDetailViewModel = getViewModel(),
    id: Int = 0
) {
    //viewModel.onEvent(BeerDetailViewModel.Event.OnRequest(id))
    Button(onClick = { viewModel.onEvent(BeerDetailViewModel.Event.OnTest(id)) }) {
        Text(text = "Order beer")
    }
    /*val beerDomain = viewModel.beers.value.beerDomain.toList()
    if (beerDomain.isNotEmpty()) {
        val beerItem = beerDomain[0];

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(beerItem.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .clickable {
                        Log.d("Image", "Beer image")
                    }
            )

            Column {
                Text(
                    beerItem.name,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            Log.d("Image", "Beer image")
                        }
                        .semantics {
                            contentDescription = "The name of beer"
                        }
                )


                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    modifier = Modifier.semantics { dtActionName = "Select beer" },
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
            }
        }
    }*/
}


@Preview
@Composable
fun ButtonComponent() {
    Button(onClick = { },
        modifier = Modifier.semantics {
            contentDescription = "Order Beer"
        }
    ) {
        Text(text = "Order")
    }
}