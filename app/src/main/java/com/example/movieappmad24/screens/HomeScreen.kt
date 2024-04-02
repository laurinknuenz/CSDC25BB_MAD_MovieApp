@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.movieappmad24.composables.SimpleBottomAppBar
import com.example.movieappmad24.composables.MovieList
import com.example.movieappmad24.composables.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(text = "FHCW Movie App MAD24", scrollBehavior = scrollBehavior, false, navController)
        },
        bottomBar = {
            SimpleBottomAppBar(
                mapOf(
                    "Home" to Icons.Filled.Home,
                    "Watchlist" to Icons.Filled.Star
                )
            )
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            MovieList(movies = getMovies(), navController)
        }
    }
}