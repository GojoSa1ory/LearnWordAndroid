package com.example.myapplication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.presentation.core.ui.theme.MediumGray

@Composable
fun WordCard (
    modifier: Modifier = Modifier,
    word: WordModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .background(MediumGray)
            .padding(10.dp),

    ) {

        word.language?.let { Text(it.language) }

        Text(
            modifier = Modifier.padding(bottom = 4.dp),
            text = "Language",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Text(
            text = word.mainWord,
            style = TextStyle(
                color = Color.Black,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = word.translatedWord,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .height(1.dp)
                .background(Color.DarkGray)
        )

        word.wordDescription?.let { des ->
            Text(
                text = des,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun wordCardPreview () {
//    WordCard(word = WordModel())
//}