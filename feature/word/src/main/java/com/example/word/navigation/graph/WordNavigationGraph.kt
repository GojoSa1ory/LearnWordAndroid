package com.example.word.navigation.graph

sealed class WordNavigationGraph(
    val route: String
) {
    object MainWordScreen: WordNavigationGraph(route = "main_word_screen")
    object CreateWordScreen: WordNavigationGraph(route = "create_word_screen")
}