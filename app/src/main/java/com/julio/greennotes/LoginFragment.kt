package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Buton to make login
        val buttonMakeLogin : Button = view.findViewById(R.id.btn_make_login)
        val buttonSingUp : Button = view.findViewById(R.id.btn_sing_up)



        buttonMakeLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToAuthenticationFragment()
            findNavController().navigate(action)
        }

        buttonSingUp.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSigningUpFragment()
            findNavController().navigate(action)
        }


    }


}

