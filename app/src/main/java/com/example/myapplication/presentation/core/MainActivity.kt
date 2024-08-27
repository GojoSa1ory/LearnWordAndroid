package com.example.myapplication.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.constants.ui.theme.MyApplicationTheme
import com.example.data.di.dataModule
import com.example.database.di.databaseModule
import com.example.language.di.languageModule
import com.example.myapplication.presentation.di.appModule
import com.example.myapplication.presentation.navigation.BottomNavBar
import com.example.myapplication.presentation.navigation.host.RootNavHost
import com.example.word.di.wordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
            androidContext(this@MainActivity)
            modules(
                databaseModule,
                dataModule,
                appModule,
                wordModule,
                languageModule,
            )
        }
        setContent {

            val navController = rememberNavController()

            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                    bottomBar = { BottomNavBar(navController = navController) }
                ) { innerPadding ->
                    RootNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
}
