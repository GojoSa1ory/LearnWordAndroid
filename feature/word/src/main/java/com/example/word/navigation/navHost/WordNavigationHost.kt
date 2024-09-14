package com.example.word.navigation.navHost

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.word.navigation.graph.WordNavigationGraph
import com.example.word.screen.create.CreateScreen
import com.example.word.screen.main.WordScreen


fun NavGraphBuilder.wordScreens(navHostController: NavHostController) {
    composable<WordNavigationGraph.VocabularyScreen> {
        WordScreen {
            navHostController.navigate(
                route = WordNavigationGraph.CreateWordScreen,
            ) {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
                restoreState = true
            }
        }
    }
    composable<WordNavigationGraph.CreateWordScreen> {
        CreateScreen {
            navHostController.popBackStack()
        }
    }
}

