package com.example.game.screen.entertranslate.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.screen.entertranslate.component.ShowAnswer
import com.example.game.screen.entertranslate.component.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun EnterTranslateScreen(
    model: EnterTranslateScreenViewModel = koinViewModel(),
    id: Int,
    closeGame: () -> Unit,
    showStatsScreen: (correntAnswersCount: Int, wordsCount: Int) -> Unit
) {

    LaunchedEffect(key1 = true) {
        model.handleIntent(intent = EnterTranslateScreenIntent.GetWords(id))
    }

    val state by model.state

    Scaffold(
        topBar = {
            TopBar {
                closeGame()
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            state.currentWord?.let { it ->

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier.padding(bottom = 15.dp),
                    text = it.mainWord,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                OutlinedTextField(
                    singleLine = true,
                    value = state.userTranslate,
                    shape = RoundedCornerShape(15.dp),
                    onValueChange = {
                        model.onUserTranslate(it)
                    }
                )

                Column(
                    modifier = Modifier.padding(vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    state.currentWord?.translatedWord?.let {
                        if (state.isShowAnswer) {
                            ShowAnswer(
                                isCurrent = state.isCorrect,
                                answer = it
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .width(170.dp),
                        onClick = {
                            model.handleIntent(EnterTranslateScreenIntent.CheckAnswer)
                        },
                        enabled = model.isCheckEnable()
                    ) {
                        Text(
                            text = "Check answer",
                            color = if (state.isCorrect) Color.Green else Color.Black,
                            fontSize = 18.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp),
                    onClick = {
                        model.handleIntent(EnterTranslateScreenIntent.SwipeLanguage)
                        if(state.isShowStatsScreen) {
                            showStatsScreen(1, 2)
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

