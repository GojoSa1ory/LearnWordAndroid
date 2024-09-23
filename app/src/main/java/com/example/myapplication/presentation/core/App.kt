package com.example.myapplication.presentation.core

import android.app.Application
import com.example.data.di.dataModule
import com.example.database.di.databaseModule
import com.example.game.di.gameModule
import com.example.language.di.languageModule
import com.example.myapplication.presentation.di.appModule
import com.example.word.di.wordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    dataModule,
                    appModule,
                    wordModule,
                    languageModule,
                    gameModule
                )
            )
        }
    }
}