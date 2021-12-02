package com.julio.greennotes.api

import com.julio.greennotes.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskService {

    @POST("api/todo")
    suspend fun addTask(@Body task: Task): Response<Task>


    @DELETE("api/todo/{task}")
    suspend fun deletTaskById(
        @Path("task") id : Int
    ): Response<Task>

}