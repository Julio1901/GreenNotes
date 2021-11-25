package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julio.greennotes.service.Task
import com.julio.greennotes.service.TaskAdapter
import com.julio.greennotes.service.TaskCategory


class CardTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_task, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//            var name = view.findViewById<TextView>(R.id.editText_name).text.toString()
//            var detail = view.findViewById<TextView>(R.id.editText_details).text.toString()
//            var responsible = view.findViewById<TextView>(R.id.editText_responsible).text.toString()
//            var date = view.findViewById<TextView>(R.id.editText_date).text.toString()
//            var status = view.findViewById<TextView>(R.id.editText_status).text.toString()

            //val newTask = Task(name, detail, responsible,date, status)


    }

}





