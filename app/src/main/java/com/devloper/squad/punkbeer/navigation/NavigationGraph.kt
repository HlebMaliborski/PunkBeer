package com.devloper.squad.punkbeer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devloper.squad.feature_beer.presentation.BeerDetailCard
import com.devloper.squad.feature_beer.presentation.BeerList
import com.devloper.squad.navigation.BeerDetailNavigation
import com.devloper.squad.navigation.BeerNavigation

@Composable
fun NavigationComponent(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = BeerNavigation.beers.destination
  ) {
    composable(BeerNavigation.beers.destination) {
      BeerList()
    }
    composable(BeerDetailNavigation.route) { backStackEntry ->
      val identity = backStackEntry.arguments?.getString("id") ?: "0"
      BeerDetailCard(id = identity.toInt())
    }
  }
}