package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.language.navigation.graph.LanguageNavigationGraph
import com.example.word.navigation.graph.WordNavigationGraph
import kotlinx.serialization.Serializable

//sealed class RootNavigationGraph (
//    val route: String,
//    val icon: ImageVector,
//    val title: String
//) {
//
//    object MainWordScreen: RootNavigationGraph(
//        route = WordNavigationGraph.MainWordScreen().route,
//        title = WordNavigationGraph.MainWordScreen().title,
//        icon = WordNavigationGraph.MainWordScreen().icon
//    )
//
//    object Games: RootNavigationGraph(
//        route = GameNavigationGraph.ChooseGame().route,
//        title = GameNavigationGraph.ChooseGame().title,
//        icon = GameNavigationGraph.ChooseGame().icon
//    )
//
//    object MainLanguageScreen: RootNavigationGraph(
//        route = LanguageNavigationGraph.MainLanguageScreen().route,
//        title = LanguageNavigationGraph.MainLanguageScreen().title,
//        icon = LanguageNavigationGraph.MainLanguageScreen().icon
//    )
//}

data class RootRoute<T: Any>(val title: String, val route: T, val icon: ImageVector)