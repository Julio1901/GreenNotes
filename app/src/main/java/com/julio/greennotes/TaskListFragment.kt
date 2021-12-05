package com.julio.greennotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.julio.greennotes.model.Task
import com.julio.greennotes.repository.TaskRepository
import com.julio.greennotes.service.TaskOld
import com.julio.greennotes.service.TaskAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class TaskListFragment : Fragment() {
//Home onde tem a recycler view e um botão para adicionar novas tasks

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

        val buttonAddNewTask : ImageButton = view.findViewById(R.id.btn_add_new_task)
        val buttonProfile : ImageButton = view.findViewById(R.id.btn_profile)
        val buttonSaveTheWorld : ImageButton = view.findViewById(R.id.btn_save_the_world)



        buttonAddNewTask.setOnClickListener {
            val action = TaskListFragmentDirections.actionHomeFragmentToFormFragment()
            findNavController().navigate(action)
        }

        buttonProfile.setOnClickListener {
            val action = TaskListFragmentDirections.actionTaskListFragmentToProfilleFragment()
            findNavController().navigate(action)
        }

        buttonSaveTheWorld.setOnClickListener {
            val action = TaskListFragmentDirections.actionHomeToSaveTheWorld()
            findNavController().navigate(action)
        }



        //__________________________________

        //recyclerView.adapter = TaskAdapter(this.requireContext(), fakeTaskList)


//
//
//        taskViewModel.getLiveDateList().observe(this, Observer {
//
//
//        })

        //SE TIRAR ESSE MÉTODO DAQUI O RETUR DELA NÃO INICIA E O APP CRASHA
        val testList = taskViewModel.returnAllTaskList()

        //atualiza quando o db é criado


        lifecycleScope.launch {
            taskViewModel.myQueryResponse.collect {
                    response -> recyclerView.adapter = TaskAdapter(view.context, response)
            }
        }



        // recyclerView.adapter = TaskAdapter(this.requireContext(), testList)
        recyclerView.setHasFixedSize(true)






    }





}