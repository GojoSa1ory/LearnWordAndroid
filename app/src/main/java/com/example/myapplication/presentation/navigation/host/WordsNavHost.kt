package com.example.myapplication.presentation.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.presentation.navigation.graphs.RootNavigationGraph
import com.example.myapplication.presentation.navigation.graphs.WordsNavigationGraph
import com.example.myapplication.presentation.screen.home.HomeScreen
import com.example.myapplication.presentation.screen.home.createword.CreateWordScreen

fun NavGraphBuilder.WordsNav(
    navHostController: NavHostController
) {
    navigation(
        startDestination = WordsNavigationGraph.MainWordScreen.route,
        route = RootNavigationGraph.Words.route
    ) {
        composable(
            route = WordsNavigationGraph.MainWordScreen.route
        ) {
            HomeScreen {
                navHostController.navigate(WordsNavigationGraph.CreateWordScreen.route)
            }
        }

        composable(
            route = WordsNavigationGraph.CreateWordScreen.route
        ) {
            CreateWordScreen {
                navHostController.popBackStack()
            }
        }
    }
}