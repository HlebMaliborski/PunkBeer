package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.devloper.squad.feature_beer.domain.model.BeerItemDomain
import org.koin.androidx.compose.getViewModel

@Composable
fun BeerList(
  viewModel: BeerViewModel = getViewModel()
) {
  val beerDomain = viewModel.beers.value.beerDomain.toList()
  LazyColumn {
    items(beerDomain.size) { index ->
      BeerCard(beerItem = beerDomain[index]) { id ->
        viewModel.onEvent(BeerViewModel.Event.OnNavigate(id))
      }
    }
  }
}

@Preview
@Composable
fun BeerCard(
  @PreviewParameter(BeerItemDomainPreviewParameterProvider::class) beerItem: BeerItemDomain,
  onItemClick: (id: Int) -> Unit = { it ->
    Log.d("Ura", "$it")
  }
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .padding(bottom = 10.dp)
      .fillMaxWidth()
      .clickable { onItemClick(beerItem.id) }
  ) {
    Image(
      painter = rememberImagePainter(beerItem.imageUrl),
      contentDescription = null,
      modifier = Modifier.size(128.dp)
    )
    Text(beerItem.name)
  }
}

class BeerItemDomainPreviewParameterProvider : PreviewParameterProvider<BeerItemDomain> {
  override val values = sequenceOf(
    BeerItemDomain(imageUrl = "https://www.example.com/image.jpg", name = "Ura")
  )
}
