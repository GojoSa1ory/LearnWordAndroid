package com.example.game.screen.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.constants.ui.theme.MediumGray

@Composable
fun AnswerCard(
    correctWord: String,
    uncorrectWord: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(
                color = MediumGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = uncorrectWord,
            fontSize = 18.sp,
            color = Color.Red
        )

        Text(text = "-------->")

        Text(
            text = correctWord,
            fontSize = 18.sp,
            color = Color.Green
        )
    }
}