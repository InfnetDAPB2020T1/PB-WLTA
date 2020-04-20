package com.example.pb_android_radion.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.Model.Musica

import com.example.pb_android_radion.R
import com.example.pb_android_radion.ViewModel.UsuarioViewModel
import kotlinx.android.synthetic.main.fragment_player.*

/**
 * A simple [Fragment] subclass.
 */
class PlayerFragment : Fragment() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            usuarioViewModel = ViewModelProviders.of(it).get(UsuarioViewModel::class.java)
        }

        if(txtNomeMusica == usuarioViewModel.musica!!.nome){
            usuarioViewModel.musica!!.classificacao = ratingClassificacao.numStars
        }
    }
}
