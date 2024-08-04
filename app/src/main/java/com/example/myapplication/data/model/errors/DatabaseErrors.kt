package com.example.myapplication.data.model.errors

sealed class DatabaseErrors (message: String, title: String): Error(message) {

//    object DatabaseIsInvalid : DatabaseErrors(
//        title = "Database is invalid",
//        message = "Database is invalid at now.",
//    )

    object CreateFailed : DatabaseErrors(
        title = "Create failed",
        message = "Error while try create data in database.",
    )

    object ReadFailed : DatabaseErrors(
        title = "Read failed",
        message = "Error while try read data from database.",
    )

    object UpdateFailed : DatabaseErrors(
        title = "Update failed",
        message = "Error while try update data in database.",
    )

    object DeleteFailed : DatabaseErrors(
        title = "Delete failed",
        message = "Error while try delete data from database.",
    )
}