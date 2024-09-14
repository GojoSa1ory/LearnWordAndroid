package com.example.language.ui.screen.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.LanguageModel
import com.example.ui.LanguageCard

@Composable
fun LanguageList(
    languages: List<LanguageModel>,
    navigateToDetailsScreen: (langId: Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally)
    ) {
        if(languages.isEmpty()) {
            item {
                Text(text = "You don't have any language. You can create language")
            }
        } else {
            items(languages) {
                LanguageCard(language = it) {
                    navigateToDetailsScreen(it.id)
                }
            }
        }
    }
}