@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.movieappmad24.models.MoviesViewModel

@Composable
fun HomeScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                text = "FHCW Movie App MAD24",
                scrollBehavior = scrollBehavior,
                false,
                navController
            )
        },
        bottomBar = {
            SimpleBottomAppBar(navController, true)
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            MovieList(navController, moviesViewModel) { moviesViewModel.movies }
        }
    }
}