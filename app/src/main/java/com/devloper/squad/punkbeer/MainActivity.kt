package com.devloper.squad.punkbeer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.devloper.squad.navigation.NavigationManager
import com.devloper.squad.punkbeer.navigation.NavigationComponent
import com.devloper.squad.punkbeer.ui.theme.PunkbeerTheme
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

  private val navigationManager: NavigationManager by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      PunkbeerTheme {
        LaunchedEffect(Unit) {
          navigationManager.commands.collect { command ->
            if (command.destination.isNotEmpty()) {
              navController.navigate(command.destination)
            }
          }
        }
        Surface(color = MaterialTheme.colors.background) {
          NavigationComponent(navController)
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