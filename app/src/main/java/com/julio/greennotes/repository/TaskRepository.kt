package com.julio.greennotes.repository

import com.julio.greennotes.api.RetrofitInstance
import com.julio.greennotes.model.Task
import retrofit2.Response

class TaskRepository {

    suspend fun addTask(newTask : Task) : Response<Task> {
        return RetrofitInstance.api.addTask(newTask)
    }

}