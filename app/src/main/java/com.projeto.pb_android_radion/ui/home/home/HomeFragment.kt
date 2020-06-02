package com.projeto.pb_android_radion.ui.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.projeto.pb_android_radion.R
import com.projeto.pb_android_radion.viewModel.MusicaViewModel
import kotlinx.android.synthetic.main.fragment_home.*

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
          musicaViewModel.setupRecycleView(ListaDeMusicasRecycle,this.requireContext())
    }

}
