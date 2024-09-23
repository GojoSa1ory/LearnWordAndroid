package com.example.game.screen.choosegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game.R
import com.example.game.navigation.graph.GameNavigationGraph
import com.example.game.screen.choosegame.component.GameCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseGameScreen(
    id: Int,
    navigateToTranslateGame: (id: Int) -> Unit,
    navigateToChooseCorrectGame: (id: Int) -> Unit,
    close: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.main_games_screen_header_title)
                    )
                },
                actions = {
                    IconButton(onClick = {
                        close()
                    }) {
                        Icon(imageVector = Icons.Rounded.Close, contentDescription = "close" )
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp)
                .fillMaxSize()
        ) {

            Column {
                GameCard(name = "Translate") {
                    navigateToTranslateGame(id)
                }

                GameCard(name = "Choose correct") {
                    navigateToChooseCorrectGame(id)
                }
            }

        }

    }
}