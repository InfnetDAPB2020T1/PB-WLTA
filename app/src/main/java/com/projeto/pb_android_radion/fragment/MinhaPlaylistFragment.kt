package com.projeto.pb_android_radion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.viewModel.MinhaPlaylistViewModel


class MinhaPlaylistFragment : Fragment() {

    private lateinit var playlistViewModel: MinhaPlaylistViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        playlistViewModel =
            ViewModelProviders.of(this).get(MinhaPlaylistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_minha_playlist, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        playlistViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
