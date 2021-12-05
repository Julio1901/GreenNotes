package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController


class CompostagemFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compostagem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewBackToSaveWorld : ImageView = view.findViewById(R.id.imageView_back_compostagem_to_save_the_world)

        imageViewBackToSaveWorld.setOnClickListener {
            val action = CompostagemFragmentDirections.actionCompostagemFragmentToSaveTheWorld()
            findNavController().navigate(action)

        }


    }
}