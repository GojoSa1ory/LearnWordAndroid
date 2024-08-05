package com.example.myapplication.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.component.InputView
import com.example.myapplication.presentation.component.WordCard
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = HomeScreenIntent.LoadWords)
    }

    Surface(
        modifier = Modifier
            .background(Color.White)
            .padding(15.dp)
            .fillMaxSize(),

        ) {

        val state by viewModel.viewState

        LazyColumn(
            modifier = Modifier.background(Color.White),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                InputView(inputValue = viewModel.request) { value ->
                    viewModel.updateRequest(value)
                    viewModel.handleIntent(HomeScreenIntent.Search(viewModel.request))
                }
            }

            if(state.isLoading) {
                item {
                    Text("Loading")
                }
            } else if (state.words.isEmpty()) {
                item {
                    Text("No words")
                }
            } else {
                items(state.words) { word ->
                    WordCard(word = word)
                }
            }

            if(state.error != null) {
                item {
                    state.error?.let { error ->
                        Text(error)
                    }
                }
            }

        }

    }
}



