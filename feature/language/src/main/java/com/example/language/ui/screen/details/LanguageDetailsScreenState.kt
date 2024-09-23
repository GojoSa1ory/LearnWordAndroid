package com.example.language.ui.screen.details

import android.icu.text.CaseMap.Title
import com.example.domain.model.LanguageAndWordsModel


data class LanguageDetailsScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val isEditMode: Boolean = false,
    val newTitle: String = "",
    val errorMessage: String? = null,
    val languageAndWords: LanguageAndWordsModel? = null
)