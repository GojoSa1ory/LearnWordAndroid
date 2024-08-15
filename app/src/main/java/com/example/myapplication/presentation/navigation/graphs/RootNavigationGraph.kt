package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RootNavigationGraph(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home: RootNavigationGraph(
        route = "home_screen",
        icon = Icons.Default.Home,
        title = "Home"
    )

    object Games: RootNavigationGraph(
        route = "games_screen",
        icon = Icons.Default.PlayArrow,
        title = "Games"
    )

    object Languages: RootNavigationGraph(
        route = "languages_screen",
        icon = Icons.Default.Menu,
        title = "Languages"
    )
}