package com.julio.greennotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel (private val taskRepository: TaskRepository) : ViewModel(){


    //aqui fazedos as funções inserindo no remoto e no local
    fun addTask(newTask : Task){
        viewModelScope.launch {
            taskRepository.addTask(newTask)
        }
    }


    fun addTaskInDb(newTask: Task){
        viewModelScope.launch {
            taskRepository.addTaskInDb(newTask)
        }
    }

    fun deletTaskRemote(task: Task){
        viewModelScope.launch {
            taskRepository.deletTaskRemote(task.id)
        }
    }

    fun updateRemoteTask(task: Task){
        viewModelScope.launch {
            taskRepository.updateRemoteTask(task)
        }
    }
}