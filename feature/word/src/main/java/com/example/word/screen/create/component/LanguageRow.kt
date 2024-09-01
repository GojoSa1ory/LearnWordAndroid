package com.example.word.screen.create.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.LanguageModel

@Composable
fun LanguageRow(
    languages: List<LanguageModel>,
    selectLanguage: (Int) -> Unit,
) {

    var selectedLang by remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .sizeIn(
                maxHeight = 200.dp
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        items(languages) {
            FilterChip(
                modifier = Modifier
                    .wrapContentWidth(
                        align = Alignment.CenterHorizontally
                    )
                    .width(130.dp)
                    .height(60.dp)
                    .padding(horizontal = 4.dp),
                selected = selectedLang == it.id,
                onClick = {
                    selectLanguage(it.id)
                    selectedLang = it.id
                },
                label = {
                    Text(
                        text = it.languageName,
                        fontSize = 22.sp
                    )
                }
            )
        }
    }

}