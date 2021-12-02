package com.julio.greennotes.repository

import android.content.Context
import com.julio.greennotes.api.RetrofitInstance
import com.julio.greennotes.dao.Dao
import com.julio.greennotes.dao.GreenNotesDataBase
import com.julio.greennotes.model.Task
import retrofit2.Response

class TaskRepository(context: Context) {

    val dataBaseinstance = GreenNotesDataBase.getDataBaseInstance(context)

    val taskDao = dataBaseinstance.dao()

    suspend fun addTask(newTask : Task) : Response<Task> {
        return RetrofitInstance.api.addTask(newTask)
    }

    suspend fun addTaskInDb(newTask: Task){
        taskDao.addTaskInDb(newTask)
    }

    suspend fun deletTaskRemote(taskId : Int) : Response<Task>{
        return RetrofitInstance.api.deletTaskById(taskId)
    }

    suspend fun updateRemoteTask(task: Task) : Response<Task>{
        return RetrofitInstance.api.updateTask(task)
    }

}