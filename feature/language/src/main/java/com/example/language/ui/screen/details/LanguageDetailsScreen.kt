package com.example.language.ui.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.WordCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageDetailsScreen(
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
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text("Back")
                }

//                Text(text = state.languageAndWords?.language?.languageName ?: "Language Details")

                Button(onClick = { /*TODO*/ }) {
                    Text("Delete")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            LazyColumn(

            ) {
                if (state.isLoading) {
                    item {
                        Text(text = "Loading")
                    }
                } else if (state.isError) {
                    state.errorMessage?.let {
                        item {
                            Text(text = it)
                        }
                    }
//                } else if (state.languageAndWords?.words?.isEmpty() == true) {
//                    item {
//                        Text(text = "No words")
//                    }
                } else {
//                    state.languageAndWords?.let { data ->
//                        items(data.words) { words ->
//                            WordCard(word = words, language = words)
//                        }
//                    }
                }
            }

        }
    }


}