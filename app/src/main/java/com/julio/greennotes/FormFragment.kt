package com.julio.greennotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.julio.greennotes.model.Task
import com.julio.greennotes.service.TaskOld
import com.julio.greennotes.service.TaskCategory
import kotlinx.android.synthetic.main.fragment_form.view.*
import com.julio.greennotes.repository.TaskRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

class FormFragment : Fragment() {

//
//    private val taskViewModel : TaskViewModel by viewModel{
//        parametersOf(TaskRepository())
 //  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val taskViewModel : TaskViewModel by viewModel{
            parametersOf(TaskRepository(view.context))
        }

        val button = view.findViewById<Button>(R.id.btn_save)
        val plainTextTitle = view.findViewById<EditText>(R.id.plainText_task_title)
        val plainTextDetails = view.findViewById<EditText>(R.id.plainText_task_details)
        val plainTextResponsible = view.findViewById<EditText>(R.id.plainText_responsible)
        val plainTextDate = view.findViewById<EditText>(R.id.plainText_date)
        val plainTextProgress = view.findViewById<EditText>(R.id.plainText_progress)

        //TODO: Refactor to open with one click
        //Calendary
        plainTextDate.setOnClickListener {
            val selecionadorDeData = MaterialDatePicker
                .Builder.datePicker().build()
            selecionadorDeData.show(this.parentFragmentManager, "MATERIAL_DATE_PICKER")
            selecionadorDeData
                .addOnPositiveButtonClickListener { dataEmMilisegundos ->
                    val data = Instant.ofEpochMilli(dataEmMilisegundos)
                        .atZone(ZoneId.of("America/Sao_Paulo"))
                        .withZoneSameInstant(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                        .toLocalDate()
                    Log.i("MaterialDatePicker", "data com LocalDate: $data")
                    //TODO: Replace it with get current hour in system
                    val formatedDate = (data.year.toString() + "-" +
                            data.monthValue.toString() + "-" +
                            data.dayOfMonth.toString() + " " +
                            "10:" +
                            "12:"+
                            "00")

                    plainTextDate.setText(formatedDate)
                    Log.i("MinhaData", formatedDate)

                }
        }

        button.setOnClickListener {
            val title = plainTextTitle.text.toString()
            val details = plainTextDetails.text.toString()
            val responsible = plainTextResponsible.text.toString()
            val formatedData = plainTextDate.text.toString()
            val progress = plainTextProgress.text.toString()


            //Validate fields
            if(!validateFields(title, details, responsible,formatedData,progress)){
                //TODO: Refactor this to generate auto id
                val newTask = Task(0,title,details,responsible,formatedData,progress)

                taskViewModel.addTask(newTask)
                taskViewModel.addTaskInDb(newTask)

                //mock task
                val fakeTask = Task(3,title,details,responsible,formatedData,progress)
                //taskViewModel.deletTaskRemote(fakeTask)
            }
        }
    }


    fun validateFields(title: String, details: String, responsible: String, formatedDate: String, progress : String) : Boolean {
        var campoVazio = ""
        var containsEmptyField = false


        if (title.isEmpty()) {
            campoVazio = "title"
        }else if (details.isEmpty()){
            campoVazio = "details"
        }else if (responsible.isEmpty()){
            campoVazio = "responsible"
        }else if (formatedDate.isEmpty()){
            campoVazio = "date"
        }else if (progress.isEmpty()){
            campoVazio = "progress"
        }
        //TODO: Refactor this
        if (campoVazio != ""){
            Toast.makeText(this.context, "Campo $campoVazio está vazio", Toast.LENGTH_LONG).show()
            containsEmptyField = true
        }
        return containsEmptyField
    }

}
