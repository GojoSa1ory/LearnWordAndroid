package com.example.myapplication.presentation.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarScreenModel (
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    object Home: BottomNavBarScreenModel(
        route = "home_screen",
        icon = Icons.Default.Home,
        title = "Home"
    )

    object Games: BottomNavBarScreenModel(
        route = "games_screen",
        icon = Icons.Default.PlayArrow,
        title = "Games"
    )

    object Languages: BottomNavBarScreenModel(
        route = "languages_screen",
        icon = Icons.Default.Menu,
        title = "Languages"
    )

}