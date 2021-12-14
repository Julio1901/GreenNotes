package com.julio.greennotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class FormFragment : Fragment() {


    private val args : FormFragmentArgs by navArgs()

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

        val buttonSave = view.findViewById<Button>(R.id.btn_save_task)
        val plainTextTitle = view.findViewById<EditText>(R.id.plainText_task_title)
        val plainTextDetails = view.findViewById<EditText>(R.id.plainText_task_details)
        val plainTextResponsible = view.findViewById<EditText>(R.id.plainText_responsible)
        val plainTextDate = view.findViewById<EditText>(R.id.plainText_date)
        val plainTextProgress = view.findViewById<EditText>(R.id.plainText_progress)
        val buttonBackToHome : ImageButton = view.findViewById(R.id.btn_back_to_home)

        buttonBackToHome.setOnClickListener {
            val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
            findNavController().navigate(action)
        }


        //DROP MENU AQUI
        val spinner: Spinner = view.findViewById(R.id.planets_spinner)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.status_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

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


                    //MUDANDO MODO COMO DATA É EXIBIDA

                    val formatedDate = (data.year.toString() + "-" +
                            data.monthValue.toString() + "-" +
                            data.dayOfMonth.toString())


                    plainTextDate.setText(formatedDate)
                    Log.i("MinhaData", formatedDate)

                }
        }



        /*Define comportamentos diferentes.
        *
        * Caso esse fragment receba argumentos no momento em que for inflado
        * o comportamento dele será alterado para que ele possa editar a task
        * que irá exibir
        *
        *
        * Caso esteja em branco, significa que ele foi inflado por um botao de "adicionar nova tarefa"
        * e o comportamento dele se adequará para que isso seja possível
        * */
        if(args.taskTitle != "defaultEmpty"){

            //Ativa botão de deletar tarefa na fragment
            val btnDeletTask : Button = view.findViewById(R.id.btn_delete_task)
            btnDeletTask.setVisibility(view.visibility)

            btnDeletTask.setOnClickListener {


                MaterialAlertDialogBuilder(view.context)
                    .setTitle(resources.getString(R.string.cancelDialogTitle))
                    .setMessage(resources.getString(R.string.cancelDialogDescription))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                        dialog.cancel()
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        val taskToDelet = Task(args.taskId, args.fragmentTitle, args.taskDetail, args.taskResponsible,
                            args.taskDate, args.taskProgress)

                        taskViewModel.deletTask(taskToDelet)


                        /*Força a atualização dos dados que servirão de base para a criação da recyclerView
                        * o app funciona sem essa chamada, porém, ela é uma garantia que a tarefa deletada
                        * não irá aparecer na lista por delay na deleção do DB local
                        * */
                        taskViewModel.atualizaRecyclerView()

                        //navega de volta para a lista com as tasks que constam no banco
                        val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
                    .show()


            }
            //Task Title
            val formTitle = view.findViewById<TextView>(R.id.textView_form_title)
            formTitle.setText(args.fragmentTitle)

            plainTextTitle.setText(args.taskTitle)
            plainTextDetails.setText(args.taskDetail)
            plainTextResponsible.setText(args.taskResponsible)
            //FORMATANDO DATA PARA DISPLAY
            val dateFormatedToDisplay = args.taskDate.subSequence(0..9)
            plainTextDate.setText(args.taskDate)
            plainTextProgress.setText(args.taskProgress)

            buttonSave.setOnClickListener {
                val title = plainTextTitle.text.toString()
                val details = plainTextDetails.text.toString()
                val responsible = plainTextResponsible.text.toString()
                //PEGA A DATA DO BANCO DE DADOS DO JEITO QUE FOI SALVA E NÃO DO JEITO QUE FOI MOSTRADA AO USUÁRIO
                val formatedData = args.taskDate
                val progress = plainTextProgress.text.toString()

                //IMPLEMENTANDO SPINNER
                val progressSpinner = spinner.getSelectedItem().toString()
                val taskToUpdate = Task(args.taskId,title, details, responsible, formatedData, progressSpinner)

                taskViewModel.updateTask(taskToUpdate)

                val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
                findNavController().navigate(action)

            }


        }else{
            buttonSave.setOnClickListener {
                val title = plainTextTitle.text.toString()
                val details = plainTextDetails.text.toString()
                val responsible = plainTextResponsible.text.toString()
                val formatedData = plainTextDate.text.toString()
                val progress = plainTextProgress.text.toString()

                //MUDANDO DATA PARA USUARIO VISUALIZAR AQUI
                val formatedDataToRetrofit = (formatedData + " " +
                        "10:" +
                        "12:"+
                        "00")


                //IMPLEMENTANDO SPINNER
                val progressSpinner = spinner.getSelectedItem().toString()

                //Validate fields
                if(!validateFields(title, details, responsible,formatedData,progressSpinner)){
                    //TODO: Refactor this to generate auto id
                    val newTask = Task(0,title,details,responsible,formatedDataToRetrofit,progressSpinner)

                    //Adiciona task local e remotamente
                    taskViewModel.addTask(newTask)

                    val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
                    findNavController().navigate(action)

                    //mock task
                    val fakeTask = Task(3,title,details,responsible,formatedData,progress)
                }
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
