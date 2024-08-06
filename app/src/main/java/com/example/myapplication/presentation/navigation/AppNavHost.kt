package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.screen.games.GamesScreen
import com.example.myapplication.presentation.screen.home.HomeScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavBarScreenModel.Home.route,
    ) {
        composable(route = BottomNavBarScreenModel.Home.route){
            HomeScreen()
        }

        composable(route = BottomNavBarScreenModel.Games.route){
            GamesScreen()
        }
    }
}

