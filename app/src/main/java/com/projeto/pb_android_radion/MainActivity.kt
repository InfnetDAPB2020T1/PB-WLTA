package com.projeto.pb_android_radion

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.projeto.pb_android_radion.R.id.*
import com.projeto.pb_android_radion.R.layout
import com.projeto.pb_android_radion.viewModel.UsuarioViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.app_bar_main.*
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        /*searchImg.setOnClickListener {
         //
            findViewById<View>(R.id.mobile_navigation)
//            findNavController(searchFragment)
        }*/
       /* //item da toolbar
        val searchViewItem = menu.findItem(R.id.menu_search)
        //SearchView
        val searchView = searchViewItem.actionView as SearchView
        // fazendo com que a searchview consuma toda a barra de ferramentas quando aberta
        searchView.maxWidth= Int.MAX_VALUE
        searchView.queryHint = "Pesquisa por músicas"
        //TROCAR DEPOIS
        users.add(User("John Oliver", "26"))
        users.add(User("Bruce Wayne", "29"))
        users.add(User("Clark Kent", "27"))
        users.add(User("Berry Allen", "23"))


        //pesquisa por cada letra
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(novoTexto: String?): Boolean {
                    //textView vazio
                    tvEmpty.visibility = View.GONE

                    if (novoTexto!!.isEmpty()) {
                        searchRecycle.layoutManager = LinearLayoutManager(applicationContext)
                    } else {
                        filteredUsers.clear()
                        for (user in users) {
                            if (user.name.toLowerCase().contains(novoTexto.toLowerCase())) {
                                filteredUsers.add(user)
                            }
                        }
                        if (filteredUsers.isEmpty()) {
                            //showing the empty textview when the list is empty
                            tvEmpty.visibility = View.VISIBLE
                        }

                        searchRecycle.adapter = filteredAdapter
                    }
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    //action when type Enter
                    return false
                }
        })*/
        return true
    }

    ///exibir info no drawer layout
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(nav_host_fragment)
        usuarioViewModel.usuarioLogado = FirebaseAuth.getInstance().currentUser
        txtVwEmail.text = usuarioViewModel.usuarioLogado!!.email
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
}