package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.julio.greennotes.model.Task


class TaskViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTaskButton : Button = view.findViewById(R.id.btn_edit_task)


        //TODO: PEGAR TASK DO BANCO DE DADOS AQUI
        val fakeTask = Task(1, "fake edit","fake edit","fake edit","2021-12-01 10:10:10",
            "fake edit")


//           Comentado por Henrique por conta de erro inesperado!
//        editTaskButton.setOnClickListener {
//
//            val action = TaskViewFragmentDirections.actionTaksViewToEditTask(
//                fakeTask.name,
//                fakeTask.description,
//                fakeTask.assignetTo,
//                fakeTask.dueDate,
//                fakeTask.status
//            )
//
//            findNavController().navigate(action)
//
//
//        }




    }

}