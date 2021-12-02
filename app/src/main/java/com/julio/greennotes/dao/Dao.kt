package com.julio.greennotes.dao

import androidx.room.*
import androidx.room.Dao
import com.julio.greennotes.model.Task
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

@Dao
interface Dao {
    //TODO: update functions and delete
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTaskInDb(task : Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun getAllTasksLocal() : Flow<List<Task>>

    @Update
    suspend fun updateRoom(task: Task)

    @Delete
    suspend fun deleteTaskLocal(task: Task)

}