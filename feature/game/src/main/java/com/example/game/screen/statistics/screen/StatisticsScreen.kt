package com.example.game.screen.statistics.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.game.screen.statistics.component.AnswersList
import com.example.game.screen.statistics.component.StatisticsRow

@Composable
fun StatisticsScreen (
    stat: GameNavigationGraph.StatsScreen,
    closeScreen: () -> Unit
) {
    val progress = stat.correctAnswersCount.toFloat() / stat.wordsCount

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(durationMillis = 1000)
        )
    }

    val motivationText = when {
        progress == 0.5f -> "Good job"
        progress < 0.5f  -> "You can better! Try again."
        else -> "You're so cool"
    }

    Scaffold(
        topBar = {
            Row {
                TextButton(onClick = {
                    closeScreen()
                }) {
                    Text(text = "X")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            StatisticsRow(
                progressTitle = "${stat.correctAnswersCount}/${stat.wordsCount}",
                text = motivationText,
                progress = progress
            )

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .shadow(
                        shape = RoundedCornerShape(15.dp),
                        elevation = 12.dp
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp),
                    )
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Correct answers",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Medium
                )

                AnswersList(
                    correctWords = stat.correctWords,
                    uncorrectWords = stat.uncorrectWords
                )
            }
        }
    }
}