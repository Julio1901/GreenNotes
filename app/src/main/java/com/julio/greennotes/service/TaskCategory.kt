package com.julio.greennotes.service

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import com.julio.greennotes.R
import kotlinx.android.synthetic.main.fragment_card_task.view.*

class TaskCategory (private val nameCategory: String){
    private lateinit var taskList : MutableList<Task>

    //TODO: Data validation
    fun addTask(view : View){
        val cardTask = view.findViewById<CardView>(R.id.cardView_formTask)

        var newTask = Task(cardTask.editText_name.text.toString(),
            cardTask.editText_details.text.toString(),
            cardTask.editText_responsible.text.toString(),
            cardTask.editText_date.text.toString(),
            cardTask.editText_status.text.toString()
            )
        taskList.add(newTask)

    }

    fun returnTaskList() = taskList

}