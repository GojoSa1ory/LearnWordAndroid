package com.example.myapplication.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.presentation.navigation.graphs.RootNavigationGraph


@Composable
fun BottomNavBar(
    navController: NavHostController
) {

    val routes = listOf(
        RootNavigationGraph.MainWordScreen,
        RootNavigationGraph.Games,
        RootNavigationGraph.MainLanguageScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isBottomBarDestination = routes.any { it.route == currentDestination?.route}

    if(isBottomBarDestination) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
//                .background(color = Color.White)
                .drawBehind {
                    drawLine(
                        Color.LightGray,
                        Offset(0f, 0f),
                        Offset(size.width, 0f),
                        3f
                    )
                }

        ) {
            routes.forEach { route ->
                BottomNavItem(
                    navController = navController,
                    title = route.title,
                    icon = route.icon,
                    route = route.route,
                    selected = currentDestination?.route == route.route
                )
            }
        }
    }

}

@Composable
fun BottomNavItem(
    navController: NavHostController,
    title: String,
    icon: ImageVector,
    route: String,
    selected: Boolean
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .alpha(if (selected) 1f else 0.5f)
            .clickable {
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
//            tint = Color.Gray,
            modifier = Modifier
                .width(35.dp)
                .height(35.dp),
        )
        Text(
            text = title,
//            color = Color.Black,
            fontSize = 18.sp
        )
    }
}

//@Composable
//@Preview(showBackground = false)
//fun PreviewNavBar() {
//    BottomNavBar(navController = rememberNavController())
//}