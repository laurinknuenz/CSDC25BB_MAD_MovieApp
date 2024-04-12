package com.example.movieappmad24.composables

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MoviesViewModel
import com.example.movieappmad24.navigation.Screen

@Composable
fun MovieList(
    navController: NavHostController,
    viewModel: MoviesViewModel,
    movies: () -> List<Movie> = { viewModel.movies }
) {
    LazyColumn {
        items(movies()) { movie ->
            MovieRow(movie,
                onItemClick = { movieId ->
                    navController.navigate(route = Screen.Detail.withId(movieId))
                },
                onFavClick = { movieId ->
                    viewModel.toggleFavoriteMovie(movieId)
                })
        }
    }
}

@Composable
fun MovieRow(
    movie: Movie,
    onItemClick: (String) -> Unit = {},
    onFavClick: (String) -> Unit = {}
) {
    var showDetails by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onItemClick(movie.id)
            },
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
                ClickableHeartIcon(
                    movie.isFavorite,
                    onFavClick = { onFavClick(movie.id) })
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
fun ClickableHeartIcon(
    isFavorite: Boolean,
    onFavClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd,
    ) {
        IconButton(onClick = { onFavClick() }) {
            Icon(
                tint = MaterialTheme.colorScheme.secondary,
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Add to favorites",
            )
        }
    }
}

@Composable
fun MoviePoster(url: String, movieTitle: String) {
    AsyncImage(
        model = url,
        contentScale = ContentScale.Crop,
        contentDescription = "$movieTitle - movie poster"
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

@Composable
fun MoviePosterGallery(movie: Movie) {
    LazyRow(content = {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .height((LocalConfiguration.current.screenHeightDp * 0.3).dp),
                shape = ShapeDefaults.Large,
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                MoviePoster(url = image, movieTitle = movie.title)
            }
        }
    })
}