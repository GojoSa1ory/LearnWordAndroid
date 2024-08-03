package com.example.myapplication.domain.entities.errors

sealed class DatabaseErrors (title: String, message: String?, cause: Throwable?): Throwable(message, cause) {

    object DatabaseIsInvalid : DatabaseErrors(
        title = "Database is invalid",
        message = "Database is invalid at now.",
        cause = null
    )

    object CreateFailed : DatabaseErrors(
        title = "Create failed",
        message = "Error while try create data in database.",
        cause = null
    )

    object ReadFailed : DatabaseErrors(
        title = "Read failed",
        message = "Error while try read data from database.",
        cause = null
    )

    object UpdateFailed : DatabaseErrors(
        title = "Update failed",
        message = "Error while try update data in database.",
        cause = null
    )

    object DeleteFailed : DatabaseErrors(
        title = "Delete failed",
        message = "Error while try delete data from database.",
        cause = null
    )
}