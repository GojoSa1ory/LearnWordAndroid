package com.example.word.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.AddButton
import com.example.ui.SearchInputView
import com.example.ui.WordsList
import org.koin.androidx.compose.koinViewModel


@Composable
fun WordScreen(
    viewModel: WordScreenViewModel = koinViewModel(),
    navigateToCreate: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = WordScreenIntent.GetWords)
    }

    val state by viewModel.state


    Box(
        modifier = Modifier
//            .background(Color.White)

            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {

            SearchInputView(
                inputValue = state.searchReq,
                modifier = Modifier.padding(bottom = 20.dp)
            ) { value ->
                viewModel.updateReq(value)
                viewModel.handleIntent(WordScreenIntent.SearchWord(state.searchReq))
            }

            if (state.isError) {
                Text(text = state.errorMessage)
            }

            if (state.isLoading) {
                Text(text = "Loading")
            }

            if(!state.isError && !state.isLoading) {
                WordsList(words = state.words) {
                    viewModel.handleIntent(intent = WordScreenIntent.DeleteWord(it))
                }
            }

        }

        AddButton(
            modifier = Modifier
                .padding(bottom = 10.dp, end = 15.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd)
        ) {
            navigateToCreate()
        }

    }

}