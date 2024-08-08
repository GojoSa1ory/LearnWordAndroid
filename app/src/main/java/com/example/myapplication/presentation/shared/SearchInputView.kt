package com.example.myapplication.presentation.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchInputView (
    modifier: Modifier = Modifier,
    inputValue: String,
    onChange: (req: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            onValueChange = { newReq ->
                onChange(newReq)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Search") },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("Request") },
            value = inputValue,
            keyboardOptions = KeyboardOptions(
                showKeyboardOnFocus = false
            )
        )
    }
}