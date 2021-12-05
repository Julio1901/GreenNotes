package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class AjudaEFeedbackFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajuda_e_feedback, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBackToProfile : ImageButton = view.findViewById(R.id.btn_back_to_profile)

        buttonBackToProfile.setOnClickListener {
            val action = AjudaEFeedbackFragmentDirections.actionAjudaEFeedbackFragmentToProfilleFragment()
            findNavController().navigate(action)
        }
    }


}