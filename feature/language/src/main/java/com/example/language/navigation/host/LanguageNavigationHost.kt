package com.example.language.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.language.navigation.graph.LanguageNavigationGraph
import com.example.language.ui.screen.details.LanguageDetailsScreen
import com.example.language.ui.screen.main.LanguageScreen


fun NavGraphBuilder.languageScreen(
    navHostController: NavHostController
) {
    composable<LanguageNavigationGraph.MainLanguageScreen> {
        LanguageScreen { langId ->
            navHostController.navigate(
                LanguageNavigationGraph
                    .LanguageDetailsScreen(langId)
            )
        }
    }

    composable<LanguageNavigationGraph.LanguageDetailsScreen>{

        val screen: LanguageNavigationGraph.LanguageDetailsScreen = it.toRoute()

        LanguageDetailsScreen(
            id = screen.id,
            navigateBack = { navHostController.popBackStack() }
        )
    }
}