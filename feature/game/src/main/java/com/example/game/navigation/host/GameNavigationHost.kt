package com.example.game.navigation.host

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.game.navigation.graph.CHOOSE_RIGHT_VARIANT_MODULE_ID
import com.example.game.navigation.graph.CORRECT_ANSWERS
import com.example.game.navigation.graph.ENTER_TRANSLATE_MODULE_ID
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.game.navigation.graph.WORDS_COUNT
import com.example.game.screen.choosegame.ChooseGameScreen
import com.example.game.screen.choosemodule.screen.ChooseModuleScreen
import com.example.game.screen.entertranslate.screen.EnterTranslateScreen
import com.example.game.screen.statistics.StatisticsScreen

fun NavGraphBuilder.gameScreen(
    navHostController: NavHostController
) {
    composable(
        route = GameNavigationGraph.ChooseGame().route
    ) {
        ChooseGameScreen {
            navHostController.navigate(
                route = GameNavigationGraph.ChooseModule.route
            ) {
                restoreState = true
                launchSingleTop = true
            }
        }
    }

    composable(
        route = GameNavigationGraph.ChooseModule.route,
    ) {
        ChooseModuleScreen(
            navigateToGame = { id ->
                navHostController.navigate(
                    route = GameNavigationGraph.EnterTranslate.passId(id)
                )
            },
            navigateBack = {
                navHostController.popBackStack()
            }
        )
    }

    composable(
        route = GameNavigationGraph.ChooseRightVariant.route,
        arguments = listOf(
            navArgument(CHOOSE_RIGHT_VARIANT_MODULE_ID) {
                type = NavType.IntType
            }
        )
    ) {  }

    composable(
        route = GameNavigationGraph.EnterTranslate.route,
        arguments = listOf(
            navArgument(ENTER_TRANSLATE_MODULE_ID) {
                type = NavType.IntType
            }
        )
    ) {
        EnterTranslateScreen(
            id = it.arguments?.getInt(ENTER_TRANSLATE_MODULE_ID) ?: 0,
            closeGame = {
                navHostController.navigate(route = GameNavigationGraph.ChooseGame().route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            showStatsScreen = { correctAnswerCount, wordsCount ->
                navHostController.navigate(
                    route = GameNavigationGraph.StatsScreen.passArguments(
                            count = correctAnswerCount,
                            wordsCount = wordsCount
                        )
                ) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
    }

    composable (
        route = GameNavigationGraph.StatsScreen.route,
        arguments = listOf(
            navArgument(CORRECT_ANSWERS) {
                type = NavType.IntType
            },
            navArgument(WORDS_COUNT) {
                type = NavType.IntType
            }
        )
    ) {
        StatisticsScreen(
            correctAnswerCount = it.arguments?.getInt(CORRECT_ANSWERS) ?: 0,
            wordsCount = it.arguments?.getInt(WORDS_COUNT) ?: 0,
            closeScreen = {
                navHostController.navigate(route = GameNavigationGraph.ChooseGame().route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}