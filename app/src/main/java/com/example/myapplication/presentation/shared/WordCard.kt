@file:JvmName("WordCardKt")

package com.example.myapplication.presentation.shared

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.presentation.core.ui.theme.MediumGray
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun WordCard(
    modifier: Modifier = Modifier,
    word: WordModel,
    onRemove: () -> Unit,
) {

    val offsetX = remember { Animatable(0f) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val corotuneScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.Red,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )

        Column(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(size = 10.dp))
                .background(MediumGray)
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {
                            corotuneScope.launch {
                                if (offsetX.value < -screenWidth / 0.5) {
                                    onRemove()
                                } else {
                                    offsetX.animateTo(0f)
                                }
                            }
                        }
                    ) { change, dragAmount ->
                        // Свайп карточки
                        corotuneScope.launch {
                            if(dragAmount < 0) {
                                offsetX.snapTo(offsetX.value + dragAmount)
                            }
                        }
                    }
                }
        ) {


//        word.language?.let { Text(it.language) }

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

                if (des != "") {
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
    }
}


//@Preview(showBackground = true)
//@Composable
//fun wordCardPreview () {
//    WordCard(word = WordModel())
//}