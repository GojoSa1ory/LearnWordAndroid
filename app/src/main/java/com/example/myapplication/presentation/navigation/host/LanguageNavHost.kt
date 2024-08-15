package com.example.myapplication.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.presentation.navigation.graphs.LANGUAGE_DETAIL_LANGID_ARG
import com.example.myapplication.presentation.navigation.graphs.LanguagesNavigationGraph
import com.example.myapplication.presentation.screen.language.LanguageScreen
import com.example.myapplication.presentation.screen.language.details.LanguageDetailsScreen

@Composable
fun LanguageNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = LanguagesNavigationGraph.LanguagesScreen.route
    ) {

        composable(route = LanguagesNavigationGraph.LanguagesScreen.route) {
            LanguageScreen { langId ->
                navHostController.navigate(
                    LanguagesNavigationGraph
                    .LanguageDetailsScreen.passLanguageId(langId))
            }
        }

        composable(
            route = LanguagesNavigationGraph.LanguageDetailsScreen.route,
            arguments = listOf(
                navArgument(LANGUAGE_DETAIL_LANGID_ARG) {
                    type = NavType.IntType
                }
            )
        ) {
            LanguageDetailsScreen(
                id = it.arguments?.getInt(LANGUAGE_DETAIL_LANGID_ARG) ?: 1
            )
        }
    }
}