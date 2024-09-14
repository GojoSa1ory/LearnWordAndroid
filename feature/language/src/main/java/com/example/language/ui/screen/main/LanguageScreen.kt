package com.example.language.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.language.ui.component.languagebottomsheet.LanguageBottomSheet
import com.example.language.ui.screen.main.component.LanguageList
import com.example.ui.AddButton
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
            .fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchInputView(inputValue = model.searchReq) { req ->
                model.handleReqChange(req)
                model.handleIntent(LanguageScreenIntent.SearchLanguage(model.searchReq))
            }

            when {
                state.isLoading -> {
                    Text(text = "Loading...")
                }
                state.error != null -> {
                    state.error?.let { Text(text = it) }
                }
                else -> {
                    LanguageList(languages = state.languages) {
                        navigateToDetailsScreen(it)
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