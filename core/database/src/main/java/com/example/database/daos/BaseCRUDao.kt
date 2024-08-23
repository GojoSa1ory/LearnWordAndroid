package com.example.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BaseCRUDao<I> {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(item: I): Long

    fun read(): Flow<List<I>>

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(item: I): Int

    @Delete()
    suspend fun delete(item: I): Int

}