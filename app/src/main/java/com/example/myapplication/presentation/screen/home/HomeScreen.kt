package com.example.myapplication.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.presentation.shared.AddButton
import com.example.myapplication.presentation.shared.SearchInputView
import com.example.myapplication.presentation.shared.WordCard
import com.example.myapplication.presentation.screen.home.components.homeBottomSheet.HomeBottomSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = HomeScreenIntent.LoadWords)
    }

    val state by viewModel.viewState

    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 15.dp)
            .padding(
                bottom = WindowInsets
                    .navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding() + 24.dp,
            )
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                SearchInputView(inputValue = viewModel.request) { value ->
                    viewModel.updateRequest(value)
                    viewModel.handleIntent(HomeScreenIntent.Search(viewModel.request))
                }
            }

            if(state.isLoading) {
                item {
                    Text(
                        text ="Loading",
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
                        text ="No words",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                items(state.words, key = { word -> word.mainWord }) { word ->
                    WordCard(word = word) {
                        viewModel.handleIntent(HomeScreenIntent.DeleteWords(word))
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
            isBottomSheetVisible = true
        }

        if(isBottomSheetVisible) {

            HomeBottomSheet {
                isBottomSheetVisible = false
            }

        }


    }

}




