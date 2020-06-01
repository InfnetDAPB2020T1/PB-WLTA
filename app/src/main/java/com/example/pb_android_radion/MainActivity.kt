package com.example.pb_android_radion

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pb_android_radion.R.id.*
import com.example.pb_android_radion.R.layout
import com.example.pb_android_radion.adapter.SearchAdapter
import com.example.pb_android_radion.model.Search
import com.example.pb_android_radion.model.User
import com.example.pb_android_radion.viewModel.SearchViewModel
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.nav_header_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(drawer_layout)
        val navView: NavigationView = findViewById(nav_view)
        val navController = findNavController(nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                nav_home, nav_playlist, nav_historico, nav_perfil, nav_search
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        //colocando as informacoes na tela de perfil
        usuarioViewModel = ViewModelProviders.of(this)[UsuarioViewModel::class.java]

    }
    //toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        searchImg.setOnClickListener {
         //
            findViewById<View>(R.id.mobile_navigation)
//            findNavController(searchFragment)
        }
        return true
    }

    ///exibir info no drawer layout
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(nav_host_fragment)
        usuarioViewModel.usuarioLogado = FirebaseAuth.getInstance().currentUser
        txtVwEmail.text = usuarioViewModel.usuarioLogado!!.email
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }


    fun pesquisaMusica(view: View) {

    }
}