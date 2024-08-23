package com.example.word.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.presentation.shared.AddButton
import com.example.myapplication.presentation.shared.SearchInputView
import com.example.myapplication.presentation.shared.SwipeToDismissRow
import com.example.myapplication.presentation.shared.WordCard

@Composable
fun WordScreen(
    viewModel: WordScreenViewModel = viewModel(),
    navigateToCreate: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = WordScreenIntent.GetWords)
    }
    
    val state by viewModel.state
    

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {

        Column {

            com.example.myapplication.presentation.shared.SearchInputView(
                inputValue = state.searchReq,
                modifier = Modifier.padding(bottom = 20.dp)
            ) { value ->
                viewModel.updateReq(value)
                viewModel.handleIntent(WordScreenIntent.SearchWord(state.searchReq))
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (state.isLoading) {
                    item {
                        Text(
                            text = "Loading",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else if (state.isError) {
                    item {
                        Text(text = state.errorMessage)
                    }
                } else if (state.words.isEmpty()) {
                    item {
                        Text(
                            text = "No words",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    items(state.words, key = { word -> word.mainWord }) { word ->
                        com.example.myapplication.presentation.shared.SwipeToDismissRow(onRemove = {
                            viewModel.handleIntent(WordScreenIntent.DeleteWord(word))
                        }) {
                            com.example.myapplication.presentation.shared.WordCard(
                                word = word,
                                languageName = word.languageName
                            )
                        }
                    }
                }

            }


        }

        com.example.myapplication.presentation.shared.AddButton(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd)
        ) {
            navigateToCreate()
        }


    }

}