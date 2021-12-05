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



    //TODO: pegar uma tarefa no banco pelo nome ou id



    val localTasksListLiveDate : MutableLiveData<List<Task>> = MutableLiveData()

    var listResponse = mutableListOf<Task>()




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
    //Lembrete: Não pode colocar  no mesmo viewScope se não ele não atualiza automaticamente
    //A list assim que a task é inserida
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


//    fun addTaskInDb(newTask: Task){
//        viewModelScope.launch {
//            taskRepository.addTaskInDb(newTask)
//        }
//    }



    /* Para essa deleção simultanea funcionar eu tenho que garantir que
    as tasks remotas são atualizadas quando o db é criado. Do contráro eu inrido
    uma tarefa com um id 1 local e remoto ela sobe com id 60, por exemplo
    *
    *
    * */
    fun deletTask(task: Task){

        //delet in retrofit api
        viewModelScope.launch {
            try {
                taskRepository.deletTaskRemote(task.id)
            }catch (e : Exception){
                //log error hehe
            }

        }
        //delet in local db
        viewModelScope.launch {
            taskRepository.deletLocalTask(task)
        }


    }



//    fun deletTaskRemote(task: Task){
//        viewModelScope.launch {
//            taskRepository.deletTaskRemote(task.id)
//        }
//    }
//
//
//    fun deletTaskLocal(task: Task){
//        viewModelScope.launch {
//            taskRepository.deletLocalTask(task)
//        }
//    }



    fun updateTask(task : Task){

        viewModelScope.launch {
            try {
                taskRepository.updateRemoteTask(task)
            }catch (e : Exception){
                // taskRepository.updateLocalTask(task)
                //log error here
            }
        }


        viewModelScope.launch {
            taskRepository.updateLocalTask(task)
        }


        //atualizando response para exibir na recycler view a alteração feita
        //TODO: Verificar se é necessário
        viewModelScope.launch {
            myQueryResponse = taskRepository.getAllLocalTasks()
        }

//        viewModelScope.launch {
//            taskRepository.updateLocalTask(task)
//        }

    }


//    fun updateTaskLocal(task: Task){
//        viewModelScope.launch {
//            taskRepository.updateLocalTask(task)
//        }
//    }
//
//
//    fun updateRemoteTask(task: Task){
//        viewModelScope.launch {
//            taskRepository.updateRemoteTask(task)
//        }
//    }

    //FAZER FUNÇÃO PARA ATUALIZAR O LOCAL COM O REMOTO AQUI

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