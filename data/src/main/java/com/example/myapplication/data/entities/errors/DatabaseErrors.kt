package com.example.myapplication.data.entities.errors

sealed class DatabaseErrors (message: String, title: String): Error(message) {

    data class CreateFailed(override val message: String) : DatabaseErrors(
        title = "Create failed",
        message = message
    )

    data class ReadFailed(override val message: String) : DatabaseErrors(
        title = "Read failed",
        message = message
    )

    data class UpdateFailed(override val message: String) : DatabaseErrors(
        title = "Update failed",
        message = message
    )

    data class DeleteFailed(override val message: String) : DatabaseErrors(
        title = "Delete failed",
        message = message
    )

}