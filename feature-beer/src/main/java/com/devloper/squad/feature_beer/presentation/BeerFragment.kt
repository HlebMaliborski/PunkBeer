package com.devloper.squad.feature_beer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class BeerFragment : Fragment() {

  companion object {
    fun newInstance() = BeerFragment()
  }

  private lateinit var viewModel: BeerViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        Text(text = "Hello world.")
      }
    }
  }
}