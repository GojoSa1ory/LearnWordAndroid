package com.example.myapplication.presentation.navigation.host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.language.navigation.host.languageScreen
import com.example.myapplication.presentation.navigation.graphs.RootNavigationGraph
import com.example.word.navigation.navHost.wordScreens


@Composable
fun RootNavHost (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = RootNavigationGraph.MainWordScreen.route
    ) {
        wordScreens(navHostController = navController)

        languageScreen(navHostController = navController)
    }
}
