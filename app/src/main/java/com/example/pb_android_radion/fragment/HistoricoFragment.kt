package com.example.pb_android_radion.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.R
import com.example.pb_android_radion.viewModel.HistoricoViewModel

class HistoricoFragment : Fragment() {

    private lateinit var historicoViewModel: HistoricoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historicoViewModel =
            ViewModelProviders.of(this).get(HistoricoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_historico, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        historicoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
