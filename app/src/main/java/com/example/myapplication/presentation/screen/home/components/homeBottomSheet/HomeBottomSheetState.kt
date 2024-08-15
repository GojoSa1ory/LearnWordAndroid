package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import com.example.myapplication.domain.models.LanguageModel

data class HomeBottomSheetState (
    val isError: Boolean = false,
    val errorMessage: String = "",
    val languages: List<LanguageModel> = emptyList()
)