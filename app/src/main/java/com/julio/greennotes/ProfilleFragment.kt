package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class ProfilleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profille, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBackToHome : ImageButton = view.findViewById(R.id.btn_profile_back_to_home)
        val buttonHelpAndFeedback : Button = view.findViewById(R.id.btn_help_and_feedback)
        val buttonLogoff : Button = view.findViewById(R.id.btn_logoff)


        buttonBackToHome.setOnClickListener {
            val action = ProfilleFragmentDirections.actionProfilleFragmentToTaskListFragment()
            findNavController().navigate(action)
        }

        buttonHelpAndFeedback.setOnClickListener {
            val action = ProfilleFragmentDirections.actionProfilleFragmentToAjudaEFeedbackFragment()
            findNavController().navigate(action)
        }

        buttonLogoff.setOnClickListener {
            val action = ProfilleFragmentDirections.actionProfileToLoginFragmentLogoff()
            findNavController().navigate(action)
        }
    }

}