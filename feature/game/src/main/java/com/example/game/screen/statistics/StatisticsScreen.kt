package com.example.game.screen.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StatisticsScreen (
    correctAnswerCount: Int,
    wordsCount: Int,
    closeScreen: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {



        }
    }
}