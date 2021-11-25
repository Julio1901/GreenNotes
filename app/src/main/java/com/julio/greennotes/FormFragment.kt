package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.julio.greennotes.service.Task
import com.julio.greennotes.service.TaskAdapter
import com.julio.greennotes.service.TaskCategory
import kotlinx.android.synthetic.main.fragment_task_list.*

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
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_oficial)

        button.setOnClickListener {
            val action = FormFragmentDirections.actionTest()
            findNavController().navigate(action)
        }
    }



}