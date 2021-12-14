package com.julio.greennotes.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.julio.greennotes.R
import com.julio.greennotes.TaskListFragmentDirections
import com.julio.greennotes.model.Task


class TaskAdapter (private val context : Context, private val taskList: List<Task>): RecyclerView.Adapter<TaskAdapter.MyViewHolder>(){

    class MyViewHolder(private val view : View): RecyclerView.ViewHolder(view){
        val nameTextView : TextView = view.findViewById(R.id.editText_name)
        val detailTextView : TextView = view.findViewById(R.id.editText_details)
        val responsibleTextView : TextView = view.findViewById(R.id.editText_responsible)
        val dateTextView : TextView = view.findViewById(R.id.editText_date)
        val statusTextView : TextView = view.findViewById(R.id.editText_status)

        val btnEditTask : ImageButton = view.findViewById(R.id.btn_edit_task_card_view)
        val btnTrashDeletTask : ImageButton = view.findViewById(R.id.btn_trash_delete_task)
        val myView = view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_task, parent,false)
        return MyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = taskList[position]
        holder.nameTextView.text = item.name
        holder.detailTextView.text = item.description
        holder.responsibleTextView.text = item.assignetTo
        holder.dateTextView.text = item.dueDate
        holder.statusTextView.text = item.status


        holder.btnEditTask.setOnClickListener ( View.OnClickListener {
            fun onClick (v : View){

                val action = TaskListFragmentDirections.actionHomeFragmentToFormFragment(
                    item.name, item.description, item.assignetTo, item.dueDate,item.status,
                    "                      EDITAR TAREFA", item.id)

                v.findNavController().navigate(action)
            }
            onClick(holder.myView)
        })

        holder.btnTrashDeletTask.setOnClickListener ( View.OnClickListener {
            fun onClick (v : View){

                //Passando argumentos para edição
                val action = TaskListFragmentDirections.actionHomeFragmentToFormFragment(
                    item.name, item.description, item.assignetTo, item.dueDate,item.status,
                    "                      EDITAR TAREFA", item.id)

                v.findNavController().navigate(action)
            }
            onClick(holder.myView)
        })

    }

    override fun getItemCount() = taskList.size
}
