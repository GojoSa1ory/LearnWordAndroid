package com.example.language.ui.screen.details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageDetailsTopBar (
    label: String,
    isEditMode: Boolean,
    newLangName: String,
    navigateBack: () -> Unit,
    deleteAction: () -> Unit,
    onTitleChange: (String) -> Unit,
    enableEditMode: () -> Unit,
    saveChanges: () -> Unit
)  {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if(isEditMode) {
            EditTopBar(
                newTitle = newLangName,
                onTitleChange = onTitleChange,
                saveChanges = { saveChanges() }
            ) {
                enableEditMode()
            }

        } else {

            DefaultTopBar(
                navigateBack = { navigateBack() },
                label = label,
                deleteAction = { deleteAction() }
            ) {
                enableEditMode()
            }

        }



    }
}


@Composable
fun EditTopBar(
    newTitle: String,
    onTitleChange: (String) -> Unit,
    saveChanges: () -> Unit,
    cancelChanges: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = {
            saveChanges()
            cancelChanges()
        }) {
            Text(
                text = "Save",
                fontSize = 19.sp
            )
        }

        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = newTitle,
            shape = RoundedCornerShape(15.dp),
            onValueChange = {
                onTitleChange(it)
            }
        )

        TextButton(onClick = {
            cancelChanges()
        }) {
            Text(
                text = "Cancel",
                fontSize = 19.sp,
                color = Color.Red
            )
        }
    }
}

@Composable
fun DefaultTopBar(
    navigateBack: () -> Unit,
    label: String,
    deleteAction: () -> Unit,
    enableEditMode: () -> Unit
) {

    var showDropDownMenu by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(onClick = { navigateBack() }) {
            Text(
                text = "< Back",
                fontSize = 19.sp
            )
        }

        Text(
            text = label,
            fontSize = 23.sp,
            fontWeight = FontWeight.SemiBold
        )

        Column {

            IconButton(onClick = { showDropDownMenu = true }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "")
            }

            DropdownMenu(expanded = showDropDownMenu, onDismissRequest = { showDropDownMenu = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Delete") },
                    onClick = { deleteAction() }
                )
                DropdownMenuItem(
                    text = { Text(text = "Edit") },
                    onClick = { enableEditMode() }
                )
            }

        }
    }
}