package com.julio.greennotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import com.julio.greennotes.service.TaskOld
import com.julio.greennotes.service.TaskAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class TaskListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_oficial)

        val taskViewModel : TaskViewModel by viewModel{
            parametersOf(TaskRepository(view.context))
        }


        //CENÁRIO DE TESTE
//        val task1 = TaskOld("Teste1", "Teste1", "Test1", "Test1", "Test1")
//        val task2 = TaskOld("Teste2", "Teste2", "Test2", "Test2", "Test2")
//        val task3 = TaskOld("Teste3", "Teste3", "Test3", "Test3", "Test3")
//        val task4 = TaskOld("Teste4", "Teste4", "Test4", "Test4", "Test4")
//        val task5 = TaskOld("Teste5", "Teste5", "Test5", "Test5", "Test5")


        //val fakeTaskList = mutableListOf(task1, task2, task3, task4, task5)

        //val allLocalTasks =taskViewModel.localTasksList.value!!



        val task1 = Task(1,"NOVA RECICLER VIEW", "Teste1", "Test1", "Test1", "Test1")
        val task2 = Task(2, "Teste2", "Teste2", "Test2", "Test2", "Test2")

        val localList = mutableListOf(task1, task2)



        //__________________________________

       //recyclerView.adapter = TaskAdapter(this.requireContext(), fakeTaskList)


//
//
//        taskViewModel.getLiveDateList().observe(this, Observer {
//
//
//        })


        recyclerView.adapter = TaskAdapter(this.requireContext(), localList)
        recyclerView.setHasFixedSize(true)

    }




}