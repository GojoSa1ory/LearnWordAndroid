package com.example.myapplication.domain.entities

data class ServiceResponse<T> (
    var data: T? = null,
    var success: Boolean = false,
    var error: String? = null
)