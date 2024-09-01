package com.example.word.screen.create.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateWordField(
    value: String,
    onChange: (String) -> Unit,
    isRequired: Boolean,
    placeholder: String
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        OutlinedTextField(
            placeholder = {
                Text(text = placeholder)
            },
            value = value,
            onValueChange = { onChange(it) },
            shape = RoundedCornerShape(15.dp)
        )
        if (isRequired) {
            Text("*required field")
        }
    }
}
