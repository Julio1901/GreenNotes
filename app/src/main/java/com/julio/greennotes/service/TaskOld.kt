package com.julio.greennotes.service



class TaskOld (var  name : String, var  details : String, var responsible : String, var date : String,
               var status: String){

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




