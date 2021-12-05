package com.julio.greennotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.julio.greennotes.repository.TaskRepository
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class RegisterFragment : Fragment() {
  //Fragmento onde o usuário coloca login e senha para autenticação

    //TODO: Nesse momento, caso o usuário seja autenticado a lista deve ser carregada do remoto para o local antes de ir para a próxima fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskViewModel : TaskViewModel by viewModel{
            parametersOf(TaskRepository(view.context))
        }

        //ATUALIZA DB LOCAL ANTES DE IR PARA O FRAGMENTE ONDE O RECYCLER VIEW SERÁ EXIBIDO
        taskViewModel.atualizarDbAoSerCriado()

        val buttonAuthenticateUser : Button = view.findViewById(R.id.btn_login_user_authentication)
        val buttonBackToLoginFragment : ImageButton = view.findViewById(R.id.btn_back_to_login_fragment)



        buttonAuthenticateUser.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        buttonBackToLoginFragment.setOnClickListener {
            val action = RegisterFragmentDirections.actionAuthenticationFragmentToLoginFragment()
            findNavController().navigate(action)
        }






    }
}