package com.devloper.squad.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


object BeerNavigation {

  val default = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = ""

  }

  val beers = object : NavigationCommand {

    override val arguments = emptyList<NamedNavArgument>()

    override val destination = "beers"
  }

}

object BeerDetailNavigation {
  val route = "beer/{id}"
  val beerArguments = listOf(
    navArgument("id") { type = NavType.IntType }
  )

  fun beer(
    id: Int = 0
  ) = object : NavigationCommand {

    override val arguments = beerArguments

    override val destination = "beer/$id"
  }
}
