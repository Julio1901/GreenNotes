package com.julio.greennotes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.julio.greennotes.model.Task
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

@Dao
interface Dao {
    //TODO: Make methods here
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTaskInDb(task : Task)



}