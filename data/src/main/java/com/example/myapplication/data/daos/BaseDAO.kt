package com.example.myapplication.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create (item: T): Long

    fun read (): Flow<List<T>>

    @Update
    fun update (item: T): Int

    @Delete
    fun delete (item: T): Int
}