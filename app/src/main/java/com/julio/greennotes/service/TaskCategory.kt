package com.julio.greennotes.service

import android.util.Log

class TaskCategory (private val nameCategory: String){
    private lateinit var taskOldList : MutableList<TaskOld>

    //TODO: Data validation

    fun addTask(newTaskOld : TaskOld) : Boolean{
        //val cardTask = view.findViewById<CardView>(R.id.cardView_formTask)

        var hasSomethingEmpty : Boolean

//        var newTask = Task(cardTask.editText_name.text.toString(),
//            cardTask.editText_details.text.toString(),
//            cardTask.editText_responsible.text.toString(),
//            cardTask.editText_date.text.toString(),
//            cardTask.editText_status.text.toString()
//            )

        if(TaskOld.hasSomethingEmpty(newTaskOld)){
            //TODO: EXIBIR MENSAGEM DE ERRO
            Log.d("logValidation", "campo vazio encontrado")
            return false
        }else{
            Log.d("logValidation", "task adicionada com sucesso")
            return true
            taskOldList.add(newTaskOld)
        }

    }

    fun returnTaskList() = taskOldList

}