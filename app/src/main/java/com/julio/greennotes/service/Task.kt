package com.julio.greennotes.service

import android.view.View
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.fragment_card_task.view.*


//TODO: Revisar classe e segurança da mesma
//TODO: Transformar em um LiveDate
class Task (var  name : String, var  details : String, var responsible : String, var date : String,
            var status: String){


/*

TODO: Review it

    companion object{

        fun createNewTaskWithView(cardTask: View) : Task{

            var newTask = Task(cardTask.editText_name.text.toString(),
                cardTask.editText_details.text.toString(),
                cardTask.editText_responsible.text.toString(),
                cardTask.editText_date.text.toString(),
                cardTask.editText_status.text.toString()
            )
            return newTask

        }

       }
*/


    companion object {
    fun hasSomethingEmpty(task: Task): Boolean {
        var hasSomethingEmpty = false
        var name = task.name
        var details = task.details
        var responsible = task.responsible
        //Add here a validation to date format text
        var date = task.date
        var status = task.status

        val listOfAttributes = mutableListOf(name, details, responsible, date, status)

        listOfAttributes.forEach {
            if (it.isEmpty()) {
                hasSomethingEmpty = true
            }
        }
        return hasSomethingEmpty
    }
}

}




