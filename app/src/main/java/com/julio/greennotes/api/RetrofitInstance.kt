package com.julio.greennotes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.julio.greennotes.utils.Constants.Companion.BASE_TASK_URL

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_TASK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: TaskService by lazy {
        retrofit.create(TaskService::class.java)
    }
}
