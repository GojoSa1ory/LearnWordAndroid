package com.example.myapplication.data.model

data class ServiceResponse<T> (
    var data: T? = null,
    var success: Boolean = false,
    var error: String? = null
)