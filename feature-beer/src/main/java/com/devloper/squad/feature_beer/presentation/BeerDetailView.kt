package com.devloper.squad.feature_beer.presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel

@Composable
fun BeerDetailCard(
    viewModel: BeerDetailViewModel = getViewModel(),
    id: Int = 0
) {
    val beerDomain = viewModel.beers.value.beerDomain.toList()
    viewModel.onEvent(BeerDetailViewModel.Event.OnRequest(id))
    IconToggleButtonExamples1()
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

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun IconToggleButtonExamples1() {

    var checked by rememberSaveable { mutableStateOf(false) }
    val likeIcon =
        if (checked) Icons.Filled.ThumbUp
        else Icons.Outlined.ThumbUp
    Row {
        Checkbox()
        IconToggleButtonExamples()
        Switch(checked = false, onCheckedChange = {})
        Button(
            onClick = {
                Log.d("ClickableCompose", "Hello button!")
            },
        ) {
            Text("Test button", color = Color.Black)
        }

        Text(text = "Ura", modifier = Modifier.combinedClickable(role = Role.Button,
            onClick = {
                Log.d("ClickableCompose", "Click")
            },
            onLongClick = {
                Log.d("ClickableCompose", "LongClick")
            },
            onDoubleClick = {
                Log.d("ClickableCompose", "DoubleClick")
            }
        ))
    }
    // IconToggleButton - Custom Color
   /* Checkbox()
    IconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (checked) MaterialTheme.colors.primary
                else Color.Transparent
            )
    ) {
        Icon(likeIcon, "Like")
    }*/
}
