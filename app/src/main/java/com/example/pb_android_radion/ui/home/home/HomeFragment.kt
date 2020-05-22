package com.example.pb_android_radion.ui.home.home

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pb_android_radion.R
import com.example.pb_android_radion.adapter.ListaMusicaAdapter
import com.example.pb_android_radion.model.Musica
import com.example.pb_android_radion.viewModel.MusicaViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_player.*

class HomeFragment : Fragment() {

    private lateinit var musicaViewModel: MusicaViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        musicaViewModel =
            ViewModelProviders.of(this).get(MusicaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  musicaViewModel.setupRecycleView(ListaDeMusicasRecycle,this.requireContext())

        var musicas = mutableListOf(
            Musica(R.drawable.true_damage,"True Damage", "League of Legends"),
            Musica(R.drawable.ic_home,"furacao 2000", "So pa GOD"),
            Musica(R.drawable.ic_home,"furacao 2000", "So pa GOD")

        )
        var listaMusicaAdapter = ListaMusicaAdapter(musicas)
        ListaDeMusicasRecycle.adapter = listaMusicaAdapter
        ListaDeMusicasRecycle.layoutManager = LinearLayoutManager(context)


    }
}
