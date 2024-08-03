package com.example.myapplication.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavBar(
    navController: NavHostController
) {

    val screens = listOf(
        BottomNavBarScreenModel.Home,
        BottomNavBarScreenModel.Games,
        BottomNavBarScreenModel.Languages,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentScreen =
        screens.find { it.route == currentDestination?.route } ?: BottomNavBarScreenModel.Home

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .padding(horizontal = 30.dp)
            .height(70.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(corner = CornerSize(size = 15.dp))
            ),
    ) {
        screens.forEach { screen ->
            BottomNavItem(
                navController = navController,
                screen = screen,
                selected = currentScreen == screen
            )
        }
    }

}

@Composable
fun BottomNavItem(
    navController: NavHostController,
    screen: BottomNavBarScreenModel,
    selected: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .alpha(if (selected) 1f else 0.5f)
            .clickable {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
    ) {
        Icon(
            imageVector = screen.icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp),
        )
        Text(
            text = screen.title,
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
@Preview(showBackground = false)
fun PreviewNavBar() {
    BottomNavBar(navController = rememberNavController())
}