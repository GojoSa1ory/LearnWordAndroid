package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import com.example.myapplication.domain.models.WordModel

sealed class HomeBottomSheetIntent {
    object AddWord: HomeBottomSheetIntent()
}