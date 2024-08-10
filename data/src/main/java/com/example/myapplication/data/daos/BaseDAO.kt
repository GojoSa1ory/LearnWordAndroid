package com.example.myapplication.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseDAO<R, I> {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun create (item: I): Long

    fun read (): Flow<List<R>>

    @Update
    fun update (item: I): Int

    @Delete
    fun delete (item: I): Int
}