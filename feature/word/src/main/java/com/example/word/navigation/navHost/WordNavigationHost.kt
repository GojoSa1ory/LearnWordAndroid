package com.example.word.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.word.navigation.graph.WordNavigationGraph
import com.example.word.screen.main.WordScreen

@Composable
fun WordNavigationHost(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = WordNavigationGraph.MainWordScreen.route) {
        composable(route = WordNavigationGraph.MainWordScreen.route) {
            WordScreen {
                navHostController.navigate(route = WordNavigationGraph.CreateWordScreen.route)
            }
        }
        composable(route = WordNavigationGraph.CreateWordScreen.route) {

        }
    }
}