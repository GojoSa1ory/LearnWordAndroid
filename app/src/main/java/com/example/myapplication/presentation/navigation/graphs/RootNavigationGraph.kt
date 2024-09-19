package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.language.navigation.graph.LanguageNavigationGraph
import com.example.word.navigation.graph.WordNavigationGraph
import kotlinx.serialization.Serializable

data class RootRoute<T: Any> (
    val title: String,
    val route: T,
    val icon: ImageVector
)