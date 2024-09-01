package com.example.language.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.ui.unit.dp
import com.example.language.ui.component.languagebottomsheet.LanguageBottomSheet
import com.example.ui.AddButton
import com.example.ui.LanguageCard
import com.example.ui.SearchInputView
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageScreen(
    model: LanguageScreenViewModel = koinViewModel<LanguageScreenViewModel>(),
    navigateToDetailsScreen: (langId: Int) -> Unit
) {

    LaunchedEffect(key1 = true) {
        model.handleIntent(LanguageScreenIntent.GetLanguages)
    }

    val state by model.viewState
    var isOpenBottomSheet by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
//            .background(Color.White)
            .fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchInputView(inputValue = model.searchReq) { req ->
                model.handleReqChange(req)
                model.handleIntent(LanguageScreenIntent.SearchLanguage(model.searchReq))
            }

            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally)
            ) {
                if(state.isLoading) {
                    item {
                        Text(text = "Loading...")
                    }
                } else if (state.error != null) {
                    item {
                        state.error?.let { Text(text = it) }
                    }
                } else if (state.languages?.let {it.isEmpty()} == true) {
                    item {
                        Text(text = "No languages")
                    }
                } else {
                    items(state.languages) {
                        LanguageCard(language = it) {
                            navigateToDetailsScreen(it.id)
                        }
                    }
                }

            }


        }

        AddButton(
            modifier = Modifier
                .padding(bottom = 10.dp, end = 15.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd)
        ) {
            isOpenBottomSheet = true
        }

        if(isOpenBottomSheet) {
            LanguageBottomSheet {
                isOpenBottomSheet = false
            }
        }


    }
}