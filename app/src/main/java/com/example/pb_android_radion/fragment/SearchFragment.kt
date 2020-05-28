package com.example.pb_android_radion.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pb_android_radion.R
import com.example.pb_android_radion.viewModel.SearchViewModel


class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    lateinit var searchRecycle: RecyclerView
    var listaMusica = ArrayList<String>()
     var displayList = ArrayList<String>()
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.let {
            searchViewModel = ViewModelProviders.of(it).get(SearchViewModel::class.java)
        }
        //loadData()
        searchViewModel.setupRecycleView(searchRecycle, requireContext().applicationContext)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            findNavController().navigate(R.id.action_nav_home_to_nav_search)
        }
        if (searchView != null) {
           findNavController().navigate(R.id.action_nav_home_to_nav_search)
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            queryTextListener = object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String?): Boolean {
                    //displayList.clear()
                    if (newText!!.isNotEmpty()) {
                        val search = newText.toLowerCase()
                        listaMusica.forEach {
                            if (it.toLowerCase().contains(search)) {
                                displayList.add(it)
                            }
                        }
                    } else {
                        displayList.addAll(listaMusica)
                    }
                    searchRecycle.adapter?.notifyDataSetChanged()
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_search ->
                return false
            else -> {
            }
        }
        searchView?.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
/*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editext = searchView.findViewById<EditText>(R.id.search_src_text)
            editext.hint = "Pesquisa de música..."

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    //displayList.clear()
                    if (newText!!.isNotEmpty()) {
                        val search = newText.toLowerCase()
                        listaMusica.forEach {
                            if (it.toLowerCase().contains(search)) {
                                displayList.add(it)
                            }
                        }
                    } else {
                        displayList.addAll(listaMusica)
                    }
                    searchRecycle.adapter?.notifyDataSetChanged()
                    return true
                }
            })
        }
        return onCreateOptionsMenu(menu, inflater)
    }*/

    private fun loadData() {
        listaMusica.add("Afghanistan")
        listaMusica.add("Albania")
        listaMusica.add("Algeria")
        listaMusica.add("Andorra")
        listaMusica.add("Angola")
        listaMusica.add("Antigua and Barbuda")
        listaMusica.add("Argentina")
        listaMusica.add("Armenia")
        listaMusica.add("Australia")
        listaMusica.add("Austria")
        displayList.addAll(listaMusica)
    }
}





