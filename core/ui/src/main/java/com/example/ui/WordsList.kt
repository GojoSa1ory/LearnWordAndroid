package com.example.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.WordModel

@Composable
fun WordsList(
    words: List<WordModel>,
    onDelete: (word: WordModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(words.isEmpty()) {
            item {
                Text(text = "No words")
            }
        }

        items(words, key = { word -> word.mainWord }) { word ->
            SwipeToDismissRow(onRemove = {
                onDelete(word)
            }) {
                WordCard(
                    word = word,
                    languageName = word.languageName
                )
            }
        }

    }
}