package com.example.language.navigation.graph

import kotlinx.serialization.Serializable

@Serializable
sealed class LanguageNavigationGraph {

    @Serializable
    data object MainLanguageScreen: LanguageNavigationGraph()

    @Serializable
    data class LanguageDetailsScreen(val id: Int): LanguageNavigationGraph()

}