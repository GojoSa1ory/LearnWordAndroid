package com.example.myapplication.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.game.navigation.host.gameScreen
import com.example.language.navigation.host.languageScreen
import com.example.word.navigation.graph.WordNavigationGraph
import com.example.word.navigation.navHost.wordScreens


@Composable
fun RootNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = WordNavigationGraph.VocabularyScreen
    ) {

        wordScreens(navHostController = navController)

        languageScreen(navHostController = navController)

        gameScreen(navHostController = navController)
    }
}
