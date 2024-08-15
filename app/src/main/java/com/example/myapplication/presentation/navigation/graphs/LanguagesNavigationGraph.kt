package com.example.myapplication.presentation.navigation.graphs

const val LANGUAGE_DETAIL_LANGID_ARG = "id"

sealed class LanguagesNavigationGraph(
    val route: String
) {
    object LanguagesScreen: LanguagesNavigationGraph(route = "languages_screen")
    object LanguageDetailsScreen: LanguagesNavigationGraph(route = "language_details_screen/{$LANGUAGE_DETAIL_LANGID_ARG}") {
        fun passLanguageId(id: Int): String {
            return LanguageDetailsScreen.route.replace(oldValue = "{${LANGUAGE_DETAIL_LANGID_ARG}}", newValue = id.toString())
        }
    }
}