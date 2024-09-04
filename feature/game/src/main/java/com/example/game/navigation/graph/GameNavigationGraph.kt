package com.example.game.navigation.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

const val CHOOSE_RIGHT_VARIANT_MODULE_ID = "id"
const val ENTER_TRANSLATE_MODULE_ID = "id"
const val CORRECT_ANSWERS = "correct_answers"
const val WORDS_COUNT = "words_count"

sealed class GameNavigationGraph(
    val title: String,
    val route: String
) {

    data class ChooseGame(
        val icon: ImageVector = Icons.Outlined.PlayArrow
    ): GameNavigationGraph(
        route = "choose_game_screen",
        title= "Games",
    )

    data object ChooseModule: GameNavigationGraph(
        route = "choose_module_screen",
        title = "Choose module"
    )

    data object ChooseRightVariant: GameNavigationGraph(
        route = "choose_right_variant_screen/{$CHOOSE_RIGHT_VARIANT_MODULE_ID}",
        title = "Choose right variant"
    ) {
        fun passId(id: Int): String {
            return ChooseRightVariant.route.replace(oldValue = "{${CHOOSE_RIGHT_VARIANT_MODULE_ID}}", newValue = id.toString())
        }
    }

    data object EnterTranslate: GameNavigationGraph(
        route = "enter_translate_screen/{$ENTER_TRANSLATE_MODULE_ID}",
        title = "Enter translate"
    ) {
        fun passId(id: Int): String {
            return EnterTranslate.route.replace(oldValue = "{${CHOOSE_RIGHT_VARIANT_MODULE_ID}}", newValue = id.toString())
        }
    }

    data object StatsScreen: GameNavigationGraph(
        route = "stats_screen/{$CORRECT_ANSWERS}/{$WORDS_COUNT}",
        title = "Stats screen"
    ) {
        fun passArguments(count: Int, wordsCount: Int): String {
            return "stats_screen/${count}/${wordsCount}"
        }
    }


}