package com.example.myapplication.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.Migrator
import com.example.myapplication.data.dataSource.service.WordService
import com.example.myapplication.presentation.screen.games.GamesScreen
import com.example.myapplication.presentation.screen.home.HomeScreen
import com.example.myapplication.presentation.screen.home.HomeScreenBuilder
import com.example.myapplication.presentation.screen.home.HomeScreenViewModel


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
            HomeScreenBuilder().buildScreen()
        }

        composable(route = BottomNavBarScreenModel.Games.route){
            GamesScreen()
        }
    }
}

