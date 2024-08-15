package com.example.myapplication.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.navigation.graphs.RootNavigationGraph
import com.example.myapplication.presentation.screen.home.HomeScreen


@Composable
fun RootNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RootNavigationGraph.Home.route
    ) {
        composable(
            route = RootNavigationGraph.Home.route
        ) {
            HomeScreen()
        }

        composable(route = RootNavigationGraph.Games.route) {
            
        }

        composable(route = RootNavigationGraph.Languages.route) {
            LanguageNavHost()
        }
    }
}