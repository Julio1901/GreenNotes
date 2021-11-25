package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.julio.greennotes.service.Task
import com.julio.greennotes.service.TaskCategory
import kotlinx.android.synthetic.main.fragment_form.view.*

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_save)
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_oficial)
        //val cardView : CardView = view.findViewById(R.id.cardView_formTask)

        button.setOnClickListener {
            val name = view.plainText_task_title.text.toString()
            val details = view.plainText_task_details.text.toString()
            val responsible = view.plainText_responsible.text.toString()
            val date = view.plainText_date.text.toString()
            val status = view.plainText_progress.text.toString()

            //val newTask = Task.createNewTaskWithView(view)
            val newTask = Task(name, details, responsible, date, status)
                val taskCategoryObj = TaskCategory("test")

                if(taskCategoryObj.addTask(newTask)){
                    val action = FormFragmentDirections.actionTest()
                    findNavController().navigate(action)
                }else{
                    var campoVazio = ""
                    if (name.isEmpty()) {
                        campoVazio = "title"
                    }else if (details.isEmpty()){
                        campoVazio = "details"
                    }else if (responsible.isEmpty()){
                        campoVazio = "responsible"
                    }else if (date.isEmpty()){
                        campoVazio = "date"
                    }else if (status.isEmpty()){
                        campoVazio = "progress"
                    }
                    Toast.makeText(this.context, "Campo $campoVazio está vazio", Toast.LENGTH_LONG).show()
                }
        }
    }



}