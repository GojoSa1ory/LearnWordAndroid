package com.example.game.screen.statistics.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnswersList(
    correctWords: List<String>,
    uncorrectWords: List<String>
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 15.dp)
    ) {

        if (uncorrectWords.isNotEmpty()) {
            itemsIndexed(uncorrectWords) { index, item ->
                AnswerCard(correctWord = correctWords[index], uncorrectWord = item)
            }
        } else {
            item {
                Text(text = "Good job! You don't have any mistakes")
            }
        }

    }
}