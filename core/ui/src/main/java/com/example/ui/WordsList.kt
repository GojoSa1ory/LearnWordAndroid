package com.example.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.WordModel

@Composable
fun WordsList(
    words: List<WordModel>,
    onDelete: (word: WordModel) -> Unit
) {

    val isOpenDialog = remember {
        mutableStateOf(false)
    }

    var selectedWord by remember {
        mutableStateOf<WordModel?>(null)
    }

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
                isOpenDialog.value = true
                selectedWord = word
            }) {
                WordCard(
                    word = word,
                    languageName = word.languageName
                )
            }
        }

        if (isOpenDialog.value) {
            item {
                AlertDialog (
                    onDismissRequest = {
                        isOpenDialog.value = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                isOpenDialog.value = false
                                selectedWord?.let {
                                    onDelete(it)
                                }
                            },
                        ) {
                            Text(
                                text = stringResource(id = R.string.delete_alert_delete_btn_title),
                                color = Color.Red,
                                fontSize = 20.sp
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                isOpenDialog.value = false
                            },
                        ) {
                            Text(
                                text = stringResource(id = R.string.delete_alert_abort_btn_title),
                                fontSize = 20.sp
                            )
                        }
                    },

                    text = {
                        Text(
                            text = stringResource(id = R.string.delete_alert_text),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Warning,
                            contentDescription = "Warning icon"
                        )
                    }
                )
            }
        }

    }
}