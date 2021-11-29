package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.julio.greennotes.model.Task
import com.julio.greennotes.service.TaskOld
import com.julio.greennotes.service.TaskCategory
import kotlinx.android.synthetic.main.fragment_form.view.*
import com.julio.greennotes.repository.TaskRepository
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FormFragment : Fragment() {


    private val taskViewModel : TaskViewModel by viewModel{
        parametersOf(TaskRepository())
    }

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


        button.setOnClickListener {

            val fakeTask = Task(11,"TEST GREEN NOTES", "Fake Description", "Fake responsible",
                "2021-11-29 10:12:00", "Fake status")

            taskViewModel.addTask(fakeTask)
        }
    }

}