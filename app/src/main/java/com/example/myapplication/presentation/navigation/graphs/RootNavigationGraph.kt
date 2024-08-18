package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class RootNavigationGraph(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Words: RootNavigationGraph(
        route = "words_screen"
    )

    object Games: RootNavigationGraph(
        route = "games_screen"
    )

    object Languages: RootNavigationGraph(
        route = "languages_screen",
    )

}