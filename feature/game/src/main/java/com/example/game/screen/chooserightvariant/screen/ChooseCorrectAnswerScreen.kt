package com.example.game.screen.chooserightvariant.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.game.screen.chooserightvariant.screen.component.AnswerCard
import com.example.game.screen.entertranslate.screen.EnterTranslateScreenIntent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseCorrectAnswerScreen(
    id: Int,
    showStatsScreen: () -> Unit,
    close: () -> Unit,
    model: ChooseCorrectAnswerScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = id) {
        model.handleIntent(intent = ChooseCorrectAnswerScreenIntent.FetchWords(id))
    }

    val state by model.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Game") },
                actions = {
                    IconButton(onClick = { close() }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "" )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if(state.words.isEmpty()) {
                Text(text = "You don't have any words")
            }

            state.currentWord?.let {word ->
                Text(text = "${state.currentWordPosition}/${state.wordsCount}")

                Spacer(modifier = Modifier.weight(1f))

                Text(text = word.mainWord)

                Spacer(modifier = Modifier.weight(1f))

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.answers) { answer ->

                        val backgroundColor = when {
                            state.currentWord?.translatedWord == answer && state.isCorrect -> Color.Green
                            state.selectedAnswer == answer && !state.isCorrect -> Color.Red
                            else -> Color.White
                        }

                        AnswerCard(
                            text = answer,
                            color = backgroundColor
                        ) {
                            model.handleIntent(intent = ChooseCorrectAnswerScreenIntent.CheckAnswer(answer))
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp),
                    onClick = {
                        model.handleIntent(ChooseCorrectAnswerScreenIntent.SwitchAnswer)
                        if (state.isShowStatsScreen) {
                            showStatsScreen(
//                            state.correctAnswerCount,
//                            state.wordsCount,
//                            state.correctWords,
//                            state.uncorrectWords
                            )
                        }
                    },
                    enabled = state.isNextEnable
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = ""
                    )
                }
            }




        }
    }
}