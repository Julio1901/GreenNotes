package com.julio.greennotes

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import java.util.concurrent.TimeUnit


class BannerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Atrasa a chamada para ir para outro fragment. Isso fará com que o usuário veja o banner inicial
         Handler().postDelayed({
              val action = BannerFragmentDirections.actionBannerFragmentToLoginFragment()
              findNavController().navigate(action)
         }, 3000)

    }

}