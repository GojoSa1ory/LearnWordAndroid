package com.example.game.screen.statistics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.constants.ui.theme.MediumGray

@Composable
fun StatisticsRow(
    progressTitle: String,
    text: String,
    progress: Float
) {

    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .shadow(
                shape = RoundedCornerShape(15.dp),
                elevation = 12.dp
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp),
            )
            .padding(20.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                progress = {
                    progress
                },
                strokeWidth = 8.dp,
                trackColor = MediumGray
            )

            Text(text = progressTitle)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = when (text) {
                "Good job" -> Color.Magenta
                "You can better! Try again." -> Color.Blue
                else -> Color.Green
            }
        )
    }
}