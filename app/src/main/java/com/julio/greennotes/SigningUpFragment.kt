package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController


class SigningUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signing_up, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBackToLoginScreen : ImageButton = view.findViewById(R.id.btn_back_to_login_screen)
        val buttonRegisterUserInDb : Button = view.findViewById(R.id.btn_register_new_user_in_db)


        buttonBackToLoginScreen.setOnClickListener {
            val action = SigningUpFragmentDirections.actionSigningUpFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        buttonRegisterUserInDb.setOnClickListener {
            val action = SigningUpFragmentDirections.actionSigningUpFragmentToLoginFragment()
            findNavController().navigate(action)

        }
    }
}