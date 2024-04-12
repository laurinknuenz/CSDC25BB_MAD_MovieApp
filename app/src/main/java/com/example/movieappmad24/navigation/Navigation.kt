package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val moviesViewModel: MoviesViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, moviesViewModel)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(name = "movieId") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            if (movieId != null) DetailScreen(
                navController,
                moviesViewModel.movies.find { movie -> movie.id == movieId },
                onFavClick = { favMovieId ->
                    moviesViewModel.toggleFavoriteMovie(favMovieId)
                })
        }
        composable(route = Screen.Watchlist.route) {
            WatchlistScreen(navController = navController, moviesViewModel)
        }
    }
}