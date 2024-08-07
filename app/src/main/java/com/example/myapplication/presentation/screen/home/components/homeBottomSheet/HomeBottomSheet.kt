package com.example.myapplication.presentation.screen.home.components.homeBottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
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

    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = bottomSheetState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Create word",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            Column {
                OutlinedTextField(
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Main word") },
                    singleLine = true,
                    value = viewModel.wordValue,
                    onValueChange = { value ->
                        viewModel.onWordValueChange(value)
                    }
                )

                Text(
                    text = "*required field",
                    color = Color.Red
                )
            }

            Column (Modifier.padding(vertical = 10.dp)) {
                OutlinedTextField(
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Translated word") },
                    singleLine = true,
                    value = viewModel.translatedWordValue,
                    onValueChange = { value -> viewModel.onTranslatedWordValueChange(value) }
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
                onValueChange = { value -> viewModel.onDescriptionWordValueChange(value) }
            )

            ElevatedButton(
                enabled = !viewModel.isRequiredFieldEmpty(),
                onClick = {
                    viewModel.handleIntent(HomeBottomSheetIntent.AddWord)
                    if (!viewModel.showError) {
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