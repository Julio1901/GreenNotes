package com.julio.greennotes.api

import com.julio.greennotes.model.Task
import retrofit2.Response
import retrofit2.http.*

interface TaskService {

    @POST("api/todo")
    suspend fun addTask(@Body task: Task): Response<Task>

    @DELETE("api/todo/{task}")
    suspend fun deletTaskById(
        @Path("task") id : Int
    ): Response<Task>

    @PUT("api/todo")
    suspend fun updateTask(
        @Body task: Task
    ): Response<Task>

    @GET("api/todo")
    suspend fun getAllRemoteTasks(): Response<List<Task>>

}