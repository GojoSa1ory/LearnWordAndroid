package com.example.language.navigation.graph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

const val LANGUAGE_DETAIL_LANGID_ARG = "id"

sealed class LanguageNavigationGraph(val route: String) {

    data class MainLanguageScreen(
        val icon: ImageVector = Icons.Outlined.Menu,
        val title: String = "Languages"
    ): LanguageNavigationGraph(route = "languages_screen"
    )
    object LanguageDetailsScreen: LanguageNavigationGraph(route = "language_details_screen/{$LANGUAGE_DETAIL_LANGID_ARG}") {
        fun passLanguageId(id: Int): String {
            return LanguageDetailsScreen.route.replace(oldValue = "{${LANGUAGE_DETAIL_LANGID_ARG}}", newValue = id.toString())
        }
    }

}