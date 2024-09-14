package com.example.word.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.ui.AddButton
import com.example.ui.SearchInputView
import com.example.ui.WordsList
import com.example.word.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordScreen(
    viewModel: WordScreenViewModel = koinViewModel(),
    navigateToCreate: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(intent = WordScreenIntent.GetWords)
    }

    val state by viewModel.state

    val isOpenDialog = remember {
        mutableStateOf(false)
    }

    var selectedWord by remember {
        mutableStateOf<WordModel?>(null)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchInputView(
                inputValue = state.searchReq,
                modifier = Modifier.padding(bottom = 20.dp)
            ) { value ->
                viewModel.updateReq(value)
                viewModel.handleIntent(WordScreenIntent.SearchWord(state.searchReq))
            }

            when {
                state.isLoading -> Text(text = stringResource(id = R.string.loading))
                state.isError -> Text(text = state.errorMessage)
                else -> {
                    WordsList(words = state.words) {
                        isOpenDialog.value = true
                        selectedWord = it
                    }
                }
            }

            if (isOpenDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        isOpenDialog.value = false
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            isOpenDialog.value = false
                            selectedWord?.let {
                                viewModel.handleIntent(intent = WordScreenIntent.DeleteWord(it))
                            }
                        }) {
                            Text(text = "Delete", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            isOpenDialog.value = false
                        }) {
                            Text(text = "Close")
                        }
                    },
                    title = { Text(text = stringResource(id = R.string.delete_alert_title)) },
                    text = {
                        Text(
                            text = stringResource(id = R.string.delete_word_alert_text),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
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

        AddButton(
            modifier = Modifier
                .padding(bottom = 10.dp, end = 15.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd)
        ) {
            navigateToCreate()
        }

    }

}