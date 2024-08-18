package com.example.myapplication.presentation.screen.home.createword

sealed class CreateWordScreenIntent {
    object CreateWord: CreateWordScreenIntent()
    object GetLanguages: CreateWordScreenIntent()
}