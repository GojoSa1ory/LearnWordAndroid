package com.example.game.screen.entertranslate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowAnswer(
    isCurrent: Boolean,
    answer: String
) {
    Column(
        modifier = Modifier.padding(bottom = 15.dp)
    ) {
        if (isCurrent) {
            Text(
                text = "You're right",
                fontSize = 20.sp,
                color = Color.Green,
            )
        } else {
            Text(
                text = "You have a mistake. Answer: $answer",
                fontSize = 20.sp,
                maxLines = 2,
                color = Color.Red
            )
        }
    }
}