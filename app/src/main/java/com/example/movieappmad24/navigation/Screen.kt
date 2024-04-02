package com.example.movieappmad24.navigation

sealed class Screen(val route: String) {
    data object Home: Screen(route = "homescreen")
    data object Detail: Screen(route = "detailscreen")
    data object Watchlist: Screen(route = "watchlistscreen")
}