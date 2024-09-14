package com.example.language.ui.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.language.ui.screen.details.component.LanguageDetailsTopBar
import com.example.ui.WordsList
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageDetailsScreen(
    navigateBack: () -> Unit,
    model: LanguageDetailsScreenViewModel = koinViewModel(),
    id: Int
) {

    LaunchedEffect(key1 = id) {
        model.handleIntent(LanguageDetailsScreenIntent.GetLanguageAndWordsById(id))
    }

    val state by model.viewState

    Scaffold(

        modifier = Modifier.padding(horizontal = 15.dp),
        topBar = {
            LanguageDetailsTopBar(
                label = state.languageAndWords?.language
                    ?.languageName ?: "Detail screen",
                navigateBack = { navigateBack() },
                deleteAction = {
                    state.languageAndWords?.language?.let {
                        model.handleIntent(
                            intent = LanguageDetailsScreenIntent
                                .DeleteLanguageAndWords(it)
                        )
                    }

                    if(!state.isError) {
                        navigateBack()
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            when {
                state.isLoading -> Text(
                    text = "Loading",
                    fontSize = 20.sp
                )
                state.isError -> Text(
                    text = state.errorMessage ?: "Error",
                    fontSize = 20.sp
                )
                else -> {
                    state.languageAndWords?.let { data ->
                        WordsList(words = data.words) {
                            model.handleIntent(intent = LanguageDetailsScreenIntent.DeleteWord(it))
                        }
                    }
                }
            }


        }
    }


}