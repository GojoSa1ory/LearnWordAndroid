package com.example.myapplication.presentation.navigation.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

const val LANGUAGE_DETAIL_LANGID_ARG = "id"

sealed class LanguagesNavigationGraph(
    route: String,
    title: String? = null,
    icon: ImageVector? = null
): RootNavigationGraph(route, title, icon) {
    object LanguagesScreen: LanguagesNavigationGraph(
        route = "main_languages_screen",
        title = "Languages",
        icon = Icons.Default.Menu
    )
    object LanguageDetailsScreen: LanguagesNavigationGraph(route = "language_details_screen/{$LANGUAGE_DETAIL_LANGID_ARG}") {
        fun passLanguageId(id: Int): String {
            return LanguageDetailsScreen.route.replace(oldValue = "{${LANGUAGE_DETAIL_LANGID_ARG}}", newValue = id.toString())
        }
    }
}