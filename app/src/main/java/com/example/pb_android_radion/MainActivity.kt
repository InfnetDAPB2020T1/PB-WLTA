package com.example.pb_android_radion

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import androidx.recyclerview.widget.RecyclerView
import com.example.pb_android_radion.R.*
import com.example.pb_android_radion.R.id.*
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        val toolbar: Toolbar = findViewById(toolbar)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true

    }


    ///exibir info no drawer layout
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(nav_host_fragment)

      //  usuarioViewModel = ViewModelProviders.of(this)[UsuarioViewModel::class.java]

        usuarioViewModel.usuarioLogado = FirebaseAuth.getInstance().currentUser

        txtVwEmail.text = usuarioViewModel.usuarioLogado!!.email

        //imagemUsuario = usuarioViewModel.usuarioLogado!!.imagem*/
       // imagemUsuario.setImage()

        //val usuarioLogado = intent.getSerializableExtra("it") as Usuario
        /*usuarioViewModel.usuarioLogado = usuarioLogado
        txtVwNomeCompleto.text = usuarioLogado.nome
        txtVwEmail.text = usuarioLogado.email*/

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }


  /*  private fun loadData() {
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
    }*/

}