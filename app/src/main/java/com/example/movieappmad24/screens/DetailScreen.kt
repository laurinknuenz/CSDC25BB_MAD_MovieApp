@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.example.movieappmad24.composables.BottomNavigationBar
import com.example.movieappmad24.composables.TopMovieAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun DetailScreen(movieId: String, navController: NavHostController) {
    val movie = getMovies().find { movie -> movie.id == movieId }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopMovieAppBar(text = movie?.title ?: "404", scrollBehavior = scrollBehavior, true, navController)
        }
    ) { innerPadding ->
        Text(text = "Movie", modifier = Modifier.padding(innerPadding));
    }
}