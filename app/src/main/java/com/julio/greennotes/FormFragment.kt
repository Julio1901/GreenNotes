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
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class FormFragment : Fragment() {

//Fragment onde adicionamos ou editamos uma tarefa




//
//    private val taskViewModel : TaskViewModel by viewModel{
//        parametersOf(TaskRepository())
 //  }


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


        //TODO fazer validação com "deseja mesmo sair" caso tenha sido chamado pela alteração conforme Milani pediu
        buttonBackToHome.setOnClickListener {
            val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
            findNavController().navigate(action)
        }




        //TODO: DELETAR ISSO É APENAS UM TEST
//        val taskToDelet = Task(17, "tedt dao", "test dao", "test dao", "2021-12-24 10:12:00", "test dao ")
//        taskViewModel.deletTaskLocal(taskToDelet)


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
                val taskToDelet = Task(args.taskId, args.fragmentTitle, args.taskDetail, args.taskResponsible,
                    args.taskDate, args.taskProgress)

                //Deleta task criada com valores recuperados do cardView atual
                //taskViewModel.deletTaskLocal(taskToDelet)

                //MUDANDO DELEÇÃO AQUI
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
            //Task Title
            val formTitle = view.findViewById<TextView>(R.id.textView_form_title)
            formTitle.setText(args.taskTitle)

            plainTextTitle.setText(args.taskTitle)
            plainTextDetails.setText(args.taskDetail)
            plainTextResponsible.setText(args.taskResponsible)
            plainTextDate.setText(args.taskDate)
            plainTextProgress.setText(args.taskProgress)

            buttonSave.setOnClickListener {
                val title = plainTextTitle.text.toString()
                val details = plainTextDetails.text.toString()
                val responsible = plainTextResponsible.text.toString()
                val formatedData = plainTextDate.text.toString()
                val progress = plainTextProgress.text.toString()

                val taskToUpdate = Task(args.taskId,title, details, responsible, formatedData, progress)
                //taskViewModel.updateTaskLocal(taskToUpdate)




                //TESTANDO UPDATE SINCRONO

                taskViewModel.updateTask(taskToUpdate)


                val action = FormFragmentDirections.actionFormFragmentToHomeFragment()

                // taskViewModel.atualizarDbAoSerCriado()

                findNavController().navigate(action)

            }


        }else{
            buttonSave.setOnClickListener {
                val title = plainTextTitle.text.toString()
                val details = plainTextDetails.text.toString()
                val responsible = plainTextResponsible.text.toString()
                val formatedData = plainTextDate.text.toString()
                val progress = plainTextProgress.text.toString()


                //Validate fields
                if(!validateFields(title, details, responsible,formatedData,progress)){
                    //TODO: Refactor this to generate auto id
                    val newTask = Task(0,title,details,responsible,formatedData,progress)


                    //Adiciona task local e remotamente
                    taskViewModel.addTask(newTask)

                    //Se eu tiro esse e faço pela model ele não aparece na lista na mesma hora
                    //  taskViewModel.addTaskInDb(newTask)

                    val action = FormFragmentDirections.actionFormFragmentToHomeFragment()
                    findNavController().navigate(action)

                    //mock task
                    val fakeTask = Task(3,title,details,responsible,formatedData,progress)
                    //taskViewModel.deletTaskRemote(fakeTask)


                    //taskViewModel.deletTaskRemote(fakeTask)
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
