package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBackSaveWorldToHome : ImageButton = view.findViewById(R.id.btn_back_save_world_to_home)
        val buttonGoToCompostagem : ImageButton = view.findViewById(R.id.btn_go_to_compostagem)

        buttonBackSaveWorldToHome.setOnClickListener {
            val action = HomeFragmentDirections.actionSaveWorldToHome()
            findNavController().navigate(action)
        }

        buttonGoToCompostagem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCompostagemFragment()
            findNavController().navigate(action)
        }



    }

}