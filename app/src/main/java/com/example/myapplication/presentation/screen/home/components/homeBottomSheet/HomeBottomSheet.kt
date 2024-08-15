package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBottomSheet(
    viewModel: HomeBottomSheetViewModel = koinViewModel(),
    onDismissRequest: () -> Unit
) {

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(key1 = true) {
        viewModel.handleIntent(HomeBottomSheetIntent.GetLanguages)
    }

    val state by viewModel.state

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = bottomSheetState,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Create word",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 25.dp)
            )

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
                items(state.languages) {
                    FilterChip(
                        modifier = Modifier
                            .size(50.dp),
                        selected = viewModel.selectedLanguage == it,
                        onClick = { viewModel.onSelectLang(it) },
                        label = { Text(it.languageName, fontSize = 18.sp) }
                    )
                }
            }

            Column {

                OutlinedTextField(
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Main word") },
                    singleLine = true,
                    value = viewModel.wordValue,
                    onValueChange = { value ->
                        viewModel.handleWordValueChange(value)
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
                    value = viewModel.translatedWordValue,
                    onValueChange = { value -> viewModel.handleTranslatedWordValueChange(value) }
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
                value = viewModel.descriptionWordValue,
                onValueChange = { value -> viewModel.handleDescriptionWordValueChange(value) }
            )

            ElevatedButton(
                enabled = !viewModel.isRequiredFieldEmpty(),
                onClick = {
                    viewModel.handleIntent(HomeBottomSheetIntent.AddWord)
                    if (!state.isError) {
                        viewModel.clearFields()
                        onDismissRequest()
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

}