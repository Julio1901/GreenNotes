package com.julio.greennotes.service


//TODO: Revisar classe e segurança da mesma
//TODO: Transformar em um LiveDate
class TaskOld (var  name : String, var  details : String, var responsible : String, var date : String,
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
    fun hasSomethingEmpty(taskOld: TaskOld): Boolean {
        var hasSomethingEmpty = false
        var name = taskOld.name
        var details = taskOld.details
        var responsible = taskOld.responsible
        //Add here a validation to date format text
        var date = taskOld.date
        var status = taskOld.status

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




