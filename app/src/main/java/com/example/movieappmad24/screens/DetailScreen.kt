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
import com.example.movieappmad24.composables.MoviePosterGallery
import com.example.movieappmad24.composables.MovieRow
import com.example.movieappmad24.composables.SimpleTopAppBar
import com.example.movieappmad24.models.Movie

@Composable
fun DetailScreen(navController: NavHostController, movie: Movie?) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SimpleTopAppBar(
                text = movie?.title ?: "404 - Movie Not Found",
                scrollBehavior = scrollBehavior,
                true,
                navController
            )
        }
    ) { innerPadding ->
        movie?.let {
            Column(modifier = Modifier.padding(innerPadding)) {
                MovieRow(movie)
                MoviePosterGallery(movie)
            }
        }
    }
}