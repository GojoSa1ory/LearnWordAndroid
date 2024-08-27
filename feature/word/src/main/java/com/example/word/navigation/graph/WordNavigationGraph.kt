package com.example.word.navigation.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class WordNavigationGraph(
    val route: String
) {

    data class MainWordScreen(
        val title: String = "Words",
        val icon: ImageVector = Icons.Outlined.Home
    ): WordNavigationGraph(route = "main_word_screen")

    object CreateWordScreen: WordNavigationGraph(route = "create_word_screen")

}