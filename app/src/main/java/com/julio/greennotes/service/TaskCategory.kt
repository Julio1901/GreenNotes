package com.julio.greennotes.service

import android.util.Log

class TaskCategory (private val nameCategory: String){
    private lateinit var taskOldList : MutableList<TaskOld>

    fun addTask(newTaskOld : TaskOld) : Boolean{

        var hasSomethingEmpty : Boolean

        if(TaskOld.hasSomethingEmpty(newTaskOld)){

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