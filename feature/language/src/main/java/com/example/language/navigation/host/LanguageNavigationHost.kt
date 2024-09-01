package com.example.language.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.language.navigation.graph.LANGUAGE_DETAIL_LANGID_ARG
import com.example.language.navigation.graph.LanguageNavigationGraph
import com.example.language.ui.screen.details.LanguageDetailsScreen


fun NavGraphBuilder.languageScreen(
    navHostController: NavHostController
) {
    composable(route = LanguageNavigationGraph.MainLanguageScreen().route) {
        com.example.language.ui.screen.main.LanguageScreen { langId ->
            navHostController.navigate(
                LanguageNavigationGraph
                    .LanguageDetailsScreen.passLanguageId(langId)
            )
        }
    }

    composable(
        route = LanguageNavigationGraph.LanguageDetailsScreen.route,
        arguments = listOf(
            navArgument(LANGUAGE_DETAIL_LANGID_ARG) {
                type = NavType.IntType
            }
        )
    ) {
        LanguageDetailsScreen(
            id = it.arguments?.getInt(LANGUAGE_DETAIL_LANGID_ARG) ?: 1,
            navigateBack = { navHostController.popBackStack() }
        )
    }
}