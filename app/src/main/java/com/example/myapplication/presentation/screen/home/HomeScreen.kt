package com.example.myapplication.presentation.screen.home

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
import com.example.myapplication.presentation.shared.AddButton
import com.example.myapplication.presentation.shared.SearchInputView
import com.example.myapplication.presentation.shared.SwipeToDismissRow
import com.example.myapplication.presentation.shared.WordCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    navigateToCreate: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = HomeScreenIntent.LoadWords)
    }

    val state by viewModel.viewState

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {

        Column {

            SearchInputView(
                inputValue = state.searchValue,
                modifier = Modifier.padding(bottom = 20.dp)
            ) { value ->
                viewModel.updateRequest(value)
                viewModel.handleIntent(HomeScreenIntent.Search(state.searchValue))
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
                } else if (state.error != null) {
                    item {
                        state.error?.let { error ->
                            Text(error)
                        }
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
                    items(state.words, key = { word -> word.word.mainWord }) { word ->
                        SwipeToDismissRow(onRemove = {
                            viewModel.handleIntent(HomeScreenIntent.DeleteWords(word.word))
                        }) {
                            WordCard(
                                word = word.word,
                                language = word.language
                            )
                        }
                    }
                }

            }


        }

        AddButton(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd)
        ) {
            navigateToCreate()
        }


    }

}




