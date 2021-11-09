package com.devloper.squad.punkbeer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devloper.squad.feature_beer.presentation.BeerList
import com.devloper.squad.punkbeer.ui.theme.PunkbeerTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PunkbeerTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          BeerList()
        }
      }
    }
  }
}

@Preview
@Composable
fun HelloContent() {
  Column(modifier = Modifier.padding(16.dp)) {
    var name by remember { mutableStateOf("") }
    if (name.isNotEmpty()) {
      Text(
        text = "Hello, $name!",
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.h5
      )
    }
    OutlinedTextField(
      value = name,
      onValueChange = { name = it },
      label = { Text("Name") }
    )
  }
}