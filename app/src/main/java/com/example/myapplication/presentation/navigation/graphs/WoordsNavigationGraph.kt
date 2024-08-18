package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

class WordsNavigationGraph(
    route: String,
    icon: ImageVector,
    title: String
): RootNavigationGraph(route, title, icon) {

    object MainWordScreen: RootNavigationGraph(
        route = "main_words_screen",
        title = "Words",
        icon = Icons.Outlined.Star
    )

    object CreateWordScreen: RootNavigationGraph(
        route = "create_word_screen"
    )
}