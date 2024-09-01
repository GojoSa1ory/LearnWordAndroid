package com.example.word.screen.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.word.screen.create.component.CreateWordField
import com.example.word.screen.create.component.LanguageRow
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    model: CreateScreenViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        model.handleIntent(CreateScreenIntent.GetLanguages)
    }

    val state by model.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create word") },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {

            LanguageRow(languages = state.languages) { id ->
                model.handleLanguageIdChange(id)
            }

            CreateWordField(
                placeholder = "Main word",
                value = state.mainWordValue,
                onChange = model::handleMainWordChange,
                isRequired = true
            )

            CreateWordField(
                placeholder = "Translated word",
                value = state.translatedWordValue,
                onChange = model::handleTranslatedWordChange,
                isRequired = true
            )

            CreateWordField(
                placeholder = "Description",
                value = state.descriptionWord,
                onChange = model::handleDescriptionWordChange,
                isRequired = false
            )

            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(50.dp)
                    .width(200.dp),
                onClick = {
                    model.handleIntent(intent = CreateScreenIntent.CreateWord)
                    if (!state.isError) {
                        navigateBack()
                    }
                },
                enabled = !model.checkRequiredFields()
            ) {
                Text(
                    text = "Create word",
                    fontSize = 18.sp,
                )
            }

        }
    }
}

