package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.language.navigation.graph.LanguageNavigationGraph
import com.example.word.navigation.graph.WordNavigationGraph

sealed class RootNavigationGraph (
    val route: String,
    val icon: ImageVector,
    val title: String
) {

    object MainWordScreen: RootNavigationGraph(
        route = WordNavigationGraph.MainWordScreen().route,
        title = WordNavigationGraph.MainWordScreen().title,
        icon = WordNavigationGraph.MainWordScreen().icon
    )

    object Games: RootNavigationGraph(
        route = "Coming soon",
        title = "Coming soon",
        icon = Icons.Outlined.Build
    )

    object MainLanguageScreen: RootNavigationGraph(
        route = LanguageNavigationGraph.MainLanguageScreen().route,
        title = LanguageNavigationGraph.MainLanguageScreen().title,
        icon = LanguageNavigationGraph.MainLanguageScreen().icon
    )
}