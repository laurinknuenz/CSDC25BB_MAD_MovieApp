@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieappmad24.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController

@Composable
fun TopMovieAppBar(
    text: String,
    scrollBehavior: TopAppBarScrollBehavior,
    backButton: Boolean,
    navController: NavHostController
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (backButton) {
                IconButton(
                    onClick = { navController.navigateUp() },

                    ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun BottomNavigationBar(buttons: Map<String, ImageVector>) {
    BottomAppBar() {
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
}