package com.example.movieappmad24.navigation

const val DETAIL_ARGUMENT_KEY = "movieId";

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home")
    data object Detail : Screen(route = "detail/{$DETAIL_ARGUMENT_KEY}") {
        fun withId(id: String): String {
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id)
        }
    }

    data object Watchlist : Screen(route = "watchlist")
}