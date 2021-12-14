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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TaskViewModel (private val taskRepository: TaskRepository) : ViewModel(){

    val localTasksListLiveDate : MutableLiveData<List<Task>> = MutableLiveData()
    var listResponse = mutableListOf<Task>()
    lateinit var myQueryResponse: Flow<List<Task>>

    init {
        viewModelScope.launch {
            myQueryResponse = taskRepository.getAllLocalTasks()
        }
    }

    fun addTask(newTask : Task){
        viewModelScope.launch {
            try {
                taskRepository.addRemoteTask(newTask)
            }catch (e: Exception){
                //Log error here
            }

        }

        viewModelScope.launch {
            taskRepository.addTaskInDb(newTask)
        }

    }

    fun deletTask(task: Task){

        //delete in retrofit api
        viewModelScope.launch {
            try {
                taskRepository.deletTaskRemote(task.id)
            }catch (e : Exception){

            }

        }
        //delete in local db
        viewModelScope.launch {
            taskRepository.deletLocalTask(task)
        }


    }


    fun updateTask(task : Task){

        viewModelScope.launch {
            try {
                taskRepository.updateRemoteTask(task)
            }catch (e : Exception){

            }
        }


        viewModelScope.launch {
            taskRepository.updateLocalTask(task)
        }


        //atualizando response para exibir na recycler view a alteração feita
        viewModelScope.launch {
            myQueryResponse = taskRepository.getAllLocalTasks()
        }

    }

    val task1 = Task(1,"NOVA RECICLER VIEW", "Teste1", "Test1", "Test1", "Test1")
    val task2 = Task(2, "Teste2", "Teste2", "Test2", "Test2", "Test2")

    var listDoDB = mutableListOf(task1, task2)

    fun getAllLocalTasks() {
        val response = taskRepository.getAllLocalTasks()


        viewModelScope.launch {

            response.collect {
                listDoDB = it.toMutableList()
            }


            listDoDB.forEach { task ->
                listResponse.add(task)
            }
        }

        this.listResponse = listResponse

    }


    fun returnAllTaskList() : MutableList<Task>{
        getAllLocalTasks()

        Log.d("tamanho lista", listResponse.size.toString())
        return listResponse

    }


    fun atualizaRecyclerView(){
        viewModelScope.launch {
            myQueryResponse = taskRepository.getAllLocalTasks()
        }
    }


    //Atualiza db local puxando todas as tasks do remoto quando necessário
    fun atualizarDbAoSerCriado(){

        viewModelScope.launch {
            try {
                val response = taskRepository.getAllRemoteTasks()
                if(response.isSuccessful){
                    val allRemoteTasks = response.body()!!
                    allRemoteTasks.forEach {

                        val findTask = taskRepository.queryById(it.id)
                        if (findTask.first() != null){
                            taskRepository.updateLocalTask(it)
                        }else{
                            taskRepository.addTaskInDb(it)
                        }
                    }

                }else{
                    Log.d("Developer", "Erro: ${response.errorBody().toString()}")
                }
            }catch (e: Exception){
                Log.d("Developer", e.message.toString())
            }




        }

    }

}