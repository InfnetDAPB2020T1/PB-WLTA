package com.example.pb_android_radion.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.R
import com.example.pb_android_radion.ViewModel.MusicaViewModel

class PlayerFragment : Fragment() {

    private lateinit var musicaViewModel: MusicaViewModel

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
            musicaViewModel = ViewModelProviders.of(it).get(MusicaViewModel::class.java)
        }

        // Adiciona a quantidade de estrelas na classificação da musica de acordo com o nome da musica
        /*if(txtVwNomeMusica.text == musicaViewModel.musica!!.nomeMusica){
            musicaViewModel.musica!!.classificacao = ratingClassificacao.numStars
        }*/
    }

    // Método para que o componente de classificação recebe o número de estrelas de acordo
    // com o nome da musica
    /*private fun verificarClassificacao(){
        if(txtVwNomeMusica.text == musicaViewModel.musica!!.nomeMusica){
            ratingClassificacao.numStars = musicaViewModel.musica!!.classificacao
        }
    }*/
}
