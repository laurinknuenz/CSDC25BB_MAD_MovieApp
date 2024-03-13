@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        "FHCW Movie App MAD24",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar()
            {
                BottomNavigationBar(
                    mapOf(
                        "Home" to Icons.Filled.Home,
                        "Watchlist" to Icons.Filled.Star
                    )
                )
            }
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            MovieList(movies = getMovies())
        }
    }
}

@Composable
fun BottomNavigationBar(buttons: Map<String, ImageVector>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        var firstButton = true
        for (button in buttons) {
            NavigationBarItem(selected = firstButton, onClick = { },
                icon = {
                    Icon(
                        imageVector = button.value,
                        contentDescription = "Go to ${button.key}"
                    )
                }, label = { Text(text = button.key) })
            firstButton = false
        }
    }
}

@Composable
fun MovieList(movies: List<Movie> = getMovies()) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}

@Composable
fun MovieRow(movie: Movie) {
    var showDetails by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MoviePoster(movie.images[0], movie.title)
                ClickableHeartIcon()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title)
                Icon(
                    modifier = Modifier
                        .clickable {
                            showDetails = !showDetails
                        },
                    imageVector =
                    if (showDetails) Icons.Filled.KeyboardArrowDown
                    else Icons.Default.KeyboardArrowUp, contentDescription = "Show more"
                )
            }
            AnimatedVisibility(
                visible = showDetails,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
            ) {
                MovieDetails(movie)
            }
        }
    }
}

@Composable
fun ClickableHeartIcon() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.secondary,
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = "Add to favorites"
        )
    }
}

@Composable
fun MoviePoster(url: String, movie: String) {
    AsyncImage(
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = "$movie - movie poster"
    )
}

@Composable
fun MovieDetails(movie: Movie) {
    Column {
        Text(text = "Director: ${movie.director}")
        Text(text = "Released: ${movie.year}")
        Text(text = "Genre: ${movie.genre}")
        Text(text = "Actors: ${movie.actors}")
        Text(text = "Rating: ${movie.rating}")
        Divider(modifier = Modifier.padding(0.dp, 4.dp))
        Text(text = "Plot: ${movie.plot}")
    }
}