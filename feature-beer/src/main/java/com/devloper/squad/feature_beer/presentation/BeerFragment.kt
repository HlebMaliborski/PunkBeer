package com.devloper.squad.feature_beer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerFragment : Fragment() {

  private val beerViewModel: BeerViewModel by viewModel()

  companion object {
    fun newInstance() = BeerFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        ButtonExample()
      }
    }
  }

  @Composable
  fun ButtonExample() {
    Button(
      onClick = { beerViewModel.doRequest() }, colors = ButtonDefaults.textButtonColors(
        backgroundColor = Color.Red
      )
    ) {
      Text("Button")
    }
  }
}