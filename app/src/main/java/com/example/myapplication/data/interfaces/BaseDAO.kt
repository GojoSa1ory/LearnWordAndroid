package com.example.myapplication.data.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.myapplication.data.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDAO<T> {
    @Insert
    suspend fun create (item: T): Boolean

    abstract suspend fun read (): Flow<List<T>>

    @Update
    suspend fun update (item: T): Boolean

    @Delete
    suspend fun delete (item: T): Boolean
}