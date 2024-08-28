package com.example.word.screen.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(
    model: CreateScreenViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {

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
            CreateWordField(
                value = state.mainWordValue,
                onChange = model::handleMainWordChange,
                isRequired = true
            )

            CreateWordField(
                value = state.translatedWordValue,
                onChange = model::handleTranslatedWordChange,
                isRequired = true
            )

            CreateWordField(
                value = state.descriptionWord,
                onChange = model::handleDescriptionWordChange,
                isRequired = false
            )

            Button(
                onClick = { model.handleIntent(intent = CreateScreenIntent.CreateWord) },
                enabled = !model.checkRequiredFields()
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
fun CreateWordField(
    value: String,
    onChange: (String) -> Unit,
    isRequired: Boolean
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { onChange(it) },
            shape = RoundedCornerShape(15.dp)
        )
        if (isRequired) {
            Text("*required field")
        }
    }
}