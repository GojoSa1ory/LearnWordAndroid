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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.component.WordCard

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
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

        if (state.isLoading) {
            Text("Loading")
        } else {
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.words) {word ->
                    WordCard(word = word)
                }
            }
        }

        if(state.error != null) {
            Text(state.error!!)
        }

    }
}



