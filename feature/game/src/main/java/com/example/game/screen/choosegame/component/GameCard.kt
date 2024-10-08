package com.example.game.screen.choosegame.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.constants.ui.theme.MediumGray


@Composable
fun GameCard(
    name: String,
    routeTo: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp)
            .height(height = 70.dp)
            .background(color = MediumGray, shape = RoundedCornerShape(15.dp))
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                routeTo()
            }
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}