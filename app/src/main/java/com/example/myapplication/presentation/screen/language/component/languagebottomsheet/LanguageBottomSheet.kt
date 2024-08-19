package com.example.myapplication.presentation.screen.language.component.languagebottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun LanguageBottomSheet(
    model: LanguageBottomSheetViewModel = koinViewModel(),
    onDismissRequest: () -> Unit
) {

    val state by model.state

    val bottomSheetState = rememberModalBottomSheetState()


    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = bottomSheetState,

    ) {
        Column(
            Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Create word",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 25.dp)
            )

            if(state.isError) {
                Text(text = state.error)
            }
            Column {

                OutlinedTextField(
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Language name") },
                    singleLine = true,
                    value = state.languageName,
                    onValueChange = model::handleLanguageNameChange
                )

                Text(
                    text = "*required field",
                    color = Color.Red
                )
            }

            ElevatedButton(
                enabled = !model.isRequiredFieldEmpty(),
                onClick = {
                    model.handleIntent(LanguageBottomSheetIntent.CreateLanguage)
                    if (!state.isError) {
                        model.clearField()
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