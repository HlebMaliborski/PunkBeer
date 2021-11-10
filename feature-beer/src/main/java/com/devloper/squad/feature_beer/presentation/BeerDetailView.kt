package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel

@Composable
fun BeerDetailCard(
  viewModel: BeerDetailViewModel = getViewModel(),
  id: Int = 0
) {

  val beerDomain = viewModel.beers.value.beerDomain.toList()
  viewModel.onEvent(BeerDetailViewModel.Event.OnRequest(id))
  Log.d("Ura", "Ura")
/*  Row(
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
  }*/
}
