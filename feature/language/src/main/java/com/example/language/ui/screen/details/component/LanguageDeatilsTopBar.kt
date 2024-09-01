package com.example.language.ui.screen.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageDetailsTopBar (
    label: String,
    navigateBack: () -> Unit,
    deleteAction: () -> Unit
)  {
    Row(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = { navigateBack() }) {
            Text(
                text = "< Back",
                fontSize = 19.sp
            )
        }

        Text(
            text = label,
            fontSize = 23.sp,
            fontWeight = FontWeight.SemiBold
        )

        TextButton(onClick = { deleteAction() }) {
            Text(
                text = "Delete",
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Red
            )
        }
    }
}