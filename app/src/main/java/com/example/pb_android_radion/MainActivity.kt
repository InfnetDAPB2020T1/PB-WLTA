package com.example.pb_android_radion

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.example.pb_android_radion.viewModel.UsuarioViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_playlist, R.id.nav_historico, R.id.nav_perfil, R.id.nav_search
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        usuarioViewModel = ViewModelProviders.of(this)[UsuarioViewModel::class.java]

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
}