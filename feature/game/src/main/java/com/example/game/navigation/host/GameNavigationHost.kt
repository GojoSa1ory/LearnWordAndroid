package com.example.game.navigation.host

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.get
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.game.screen.choosegame.ChooseGameScreen
import com.example.game.screen.choosemodule.screen.ChooseModuleScreen
import com.example.game.screen.chooserightvariant.screen.ChooseCorrectAnswerScreen
import com.example.game.screen.chooserightvariant.screen.ChooseCorrectAnswerScreenIntent
import com.example.game.screen.entertranslate.screen.EnterTranslateScreen
import com.example.game.screen.statistics.screen.StatisticsScreen

fun NavGraphBuilder.gameScreen(
    navHostController: NavHostController
) {

    composable<GameNavigationGraph.ChooseModule> {
        ChooseModuleScreen(
            navigateToChooseGame = { id ->
                navHostController.navigate(
                    route = GameNavigationGraph.ChooseGame(id)
                ) {
                    popUpTo(GameNavigationGraph.ChooseModule) {
                        inclusive = true
                    }
                }
            }
        )
    }

    composable<GameNavigationGraph.ChooseGame> {

        val screen = it.toRoute<GameNavigationGraph.ChooseGame>()

        ChooseGameScreen(
            id = screen.id,
            navigateToTranslateGame = { id ->
                navHostController.navigate(route = GameNavigationGraph.EnterTranslate(id)) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            navigateToChooseCorrectGame = {id ->
                navHostController.navigate(route = GameNavigationGraph.ChooseRightVariant(id)) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            close = {
                navHostController.navigate(route = GameNavigationGraph.ChooseModule) {
                    popUpTo(screen) {
                        inclusive = true
                    }
                }
            }
        )
    }

    composable<GameNavigationGraph.ChooseRightVariant> {

        val screen: GameNavigationGraph.ChooseRightVariant = it.toRoute()

        ChooseCorrectAnswerScreen(
            id = screen.id,
            showStatsScreen = {
                navHostController.navigate(GameNavigationGraph.ChooseModule)
            },
            close = {
                navHostController.navigate(GameNavigationGraph.ChooseModule) {
                    popUpTo<GameNavigationGraph.ChooseRightVariant> {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

    }

    composable<GameNavigationGraph.EnterTranslate> {

        val screen: GameNavigationGraph.EnterTranslate = it.toRoute()

        EnterTranslateScreen(
            id = screen.id,
            closeGame = {
                navHostController.navigate(route = GameNavigationGraph.ChooseModule) {
                    popUpTo<GameNavigationGraph.EnterTranslate> {
                        inclusive = true
                        saveState = false
                    }
                    launchSingleTop = true
                    restoreState = true
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
                    popUpTo(GameNavigationGraph.EnterTranslate) {
                        inclusive = true
                    }
//                    launchSingleTop = true
//                    restoreState = false
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
                    popUpTo(GameNavigationGraph.StatsScreen) {
                        inclusive = true
//                        saveState = true
                    }
//                    launchSingleTop = true
//                    restoreState = false
                }
            }
        )
    }
}