package com.example.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.LanguageModel

@Composable
fun LanguageCard (
    modifier: Modifier = Modifier,
    language: LanguageModel,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .size(
                width = 180.dp,
                height = 110.dp
            )
            .background(
                color = Color.Cyan,
                shape = RoundedCornerShape(size = 15.dp)
            )
            .clickable {
                onClick()
            }
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = language.languageName,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}