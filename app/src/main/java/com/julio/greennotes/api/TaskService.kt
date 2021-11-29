package com.julio.greennotes.api

import com.julio.greennotes.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskService {

    @POST("api/todo")
    suspend fun addTask(@Body task: Task): Response<Task>



}