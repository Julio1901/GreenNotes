package com.julio.greennotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel (private val taskRepository: TaskRepository) : ViewModel(){

    fun addTask(newTask : Task){
        viewModelScope.launch {
            taskRepository.addTask(newTask)
        }
    }

}