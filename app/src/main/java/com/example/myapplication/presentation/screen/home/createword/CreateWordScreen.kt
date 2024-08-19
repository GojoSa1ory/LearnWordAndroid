package com.example.myapplication.presentation.screen.home.createword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateWordScreen (
    model: CreateWordScreenViewModel = koinViewModel(),
    navigateToBack: () -> Unit
) {

    val state by model.state

    LaunchedEffect(key1 = true) {
        model.handelIntent(CreateWordScreenIntent.GetLanguages)
    }

    Column(
        Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .heightIn(max = 150.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "Create word",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.weight(1f))
            
            TextButton(
                onClick = {
                    navigateToBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "close btn",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Black
                )
            }
        }

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(bottom = 25.dp)
                .fillMaxWidth(fraction = 0.77f)
                .heightIn(max = 220.dp),
            contentPadding = PaddingValues(10.dp),
            columns = StaggeredGridCells.Adaptive(118.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
        ) {

            if(state.languages.isEmpty()) {
                item {
                    Text("Languages not found. To create a word u need create a language first")
                }
            }

            items(state.languages) {
                FilterChip(
                    modifier = Modifier
                        .size(50.dp),
                    selected = state.languageId == it.langId,
                    onClick = { model.handleChangeLanguageId(it.langId) },
                    label = { Text(it.languageName, fontSize = 18.sp) }
                )
            }
        }

        Column {

            OutlinedTextField(
                shape = RoundedCornerShape(15.dp),
                placeholder = { Text("Main word") },
                singleLine = true,
                value = state.mainWord,
                onValueChange = { value ->
                    model.handleChangeMainWord(value)
                }
            )

            Text(
                text = "*required field",
                color = Color.Red
            )
        }

        Column(Modifier.padding(vertical = 10.dp)) {
            OutlinedTextField(
                shape = RoundedCornerShape(15.dp),
                placeholder = { Text("Translated word") },
                singleLine = true,
                value = state.translatedWord,
                onValueChange = { value -> model.handleChangeTranslatedWord(value) }
            )

            Text(
                text = "*required field",
                color = Color.Red
            )
        }

        OutlinedTextField(
            shape = RoundedCornerShape(15.dp),
            placeholder = { Text("Description to word") },
            singleLine = false,
            value = state.descriptionWord,
            onValueChange = { value -> model.handleChangeWordDescription(value) }
        )

        ElevatedButton(
            enabled = !model.checkRequiredFields(),
            onClick = {
                model.handelIntent(CreateWordScreenIntent.CreateWord)
                if (!state.isError) {
                    model.clearFields()
                    navigateToBack()
                }
            },
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.Black,
                disabledContainerColor = Color.DarkGray,
                disabledContentColor = Color.White.copy(alpha = 0.6f)
            ),
            modifier = Modifier
                .width(265.dp)
                .height(80.dp)
                .padding(vertical = 15.dp)
        ) {
            Text(
                text = "Create word",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

}