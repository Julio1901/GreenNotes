package com.julio.greennotes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel (private val taskRepository: TaskRepository) : ViewModel(){

    val localTasksListLiveDate : MutableLiveData<List<Task>> = MutableLiveData()

    val listResponse = mutableListOf<Task>()


    lateinit var myQueryResponse: Flow<List<Task>>


    init {
        viewModelScope.launch {
            myQueryResponse = taskRepository.getAllLocalTasks()
        }
    }

    //Flow here
//    fun getTaskListLocal(){
//        taskRepository.getAllLocalTasks().collect(listResponse ->)
//    }



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

    fun deletTaskLocal(task: Task){
        viewModelScope.launch {
            taskRepository.deletLocalTask(task)
        }
    }

    fun updateRemoteTask(task: Task){
        viewModelScope.launch {
            taskRepository.updateRemoteTask(task)
        }
    }


    //FAZER FUNÇÃO PARA ATUALIZAR O LOCAL COM O REMOTO AQUI
    fun getAllLocalTasks(){
        viewModelScope.launch {
            val response = taskRepository.getAllLocalTasks()


        }
    }

}