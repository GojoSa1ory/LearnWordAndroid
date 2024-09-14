package com.example.game.navigation.host

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.game.screen.choosegame.ChooseGameScreen
import com.example.game.screen.choosemodule.screen.ChooseModuleScreen
import com.example.game.screen.entertranslate.screen.EnterTranslateScreen
import com.example.game.screen.statistics.screen.StatisticsScreen

fun NavGraphBuilder.gameScreen(
    navHostController: NavHostController
) {
    composable<GameNavigationGraph.ChooseGame> {
        ChooseGameScreen {
            navHostController.navigate(
                route = GameNavigationGraph.ChooseModule
            ) {
                restoreState = true
                launchSingleTop = true
            }
        }
    }

    composable<GameNavigationGraph.ChooseModule> {
        ChooseModuleScreen(
            navigateToGame = { id ->
                navHostController.navigate(
                    route = GameNavigationGraph.EnterTranslate(id)
                ) {
                    popUpTo(GameNavigationGraph.ChooseModule) {
                        inclusive = true
                    }
                }
            },
            navigateBack = {
                navHostController.popBackStack()
            }
        )
    }

//    composable<GameNavigationGraph.ChooseRightVariant> { }

    composable<GameNavigationGraph.EnterTranslate> {

        val screen: GameNavigationGraph.EnterTranslate = it.toRoute()

        EnterTranslateScreen(
            id = screen.id,
            closeGame = {
                navHostController.navigate(route = GameNavigationGraph.ChooseGame) {
                    popUpTo(GameNavigationGraph.EnterTranslate) {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            showStatsScreen = { correctAnswerCount, wordsCount, correctWords, uncorrectWords ->
                navHostController.navigate(
                    route = GameNavigationGraph.StatsScreen(
                        correctAnswersCount = correctAnswerCount,
                        wordsCount = wordsCount,
                        correctWords = correctWords,
                        uncorrectWords = uncorrectWords
                    )
                ) {
//                    popUpTo(GameNavigationGraph.EnterTranslate) {
//                        inclusive = true
//                        saveState = false
//                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
    }

    composable<GameNavigationGraph.StatsScreen> {

        val screen: GameNavigationGraph.StatsScreen = it.toRoute()

        StatisticsScreen(
            stat = screen,
            closeScreen = {
                navHostController.navigate(route = GameNavigationGraph.ChooseGame) {
//                    popUpTo(GameNavigationGraph.StatsScreen) {
//                        inclusive = true
//                        saveState = true
//                    }
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )
    }
}