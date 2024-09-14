package com.example.game.screen.entertranslate.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.game.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun TopBar (
    closeGame: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.enter_translate_game_title)) },
        actions = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { closeGame() }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "close game"
                    )
                }
            }
        }
    )
}