package com.example.game.screen.choosemodule.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.LanguageCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseModuleScreen(
    navigateBack: () -> Unit,
    navigateToGame: (id: Int) -> Unit,
    model: ChooseScreenViewModel = koinViewModel()
) {

    LaunchedEffect(key1 = true) {
        model.handleIntent(ChooseScreenIntent.GetLanguages)
    }

    val state by model.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Choose module") },
                actions = {
                    Row {
                        TextButton(onClick = { navigateBack() }) {
                            Text(
                                text = "< Back",
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(15.dp)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(state.languages) {
                    LanguageCard(language = it) {
                        navigateToGame(it.id)
                    }
                }
            }
        }
    }
}