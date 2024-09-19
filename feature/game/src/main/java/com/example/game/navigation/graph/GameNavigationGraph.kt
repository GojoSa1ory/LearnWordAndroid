package com.example.game.navigation.graph

import kotlinx.serialization.Serializable


@Serializable
sealed class GameNavigationGraph {

    @Serializable
    data class ChooseGame(
        val id: Int
    ) : GameNavigationGraph()

    @Serializable
    data object ChooseModule : GameNavigationGraph()

    @Serializable
    data class ChooseRightVariant(
        val id: Int,
        val title: String = "Choose Choose right variant"
    ) : GameNavigationGraph()

    @Serializable
    data class EnterTranslate(
        val id: Int,
        val title: String = "Enter translate"
    ) : GameNavigationGraph()

    @Serializable
    data class StatsScreen(
        val correctAnswersCount: Int,
        val wordsCount: Int,
        val uncorrectWords: List<String>,
        val correctWords: List<String>
    ) : GameNavigationGraph()

}