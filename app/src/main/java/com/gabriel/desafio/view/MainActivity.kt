package com.gabriel.desafio.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.gabriel.desafio.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        settingsBarNavigation()

    }

    private fun settingsBarNavigation () {
        val navBar = findViewById<BottomNavigationView>(R.id.bnv_navbar)
        val navController = findNavController(R.id.fragment_container)

        navBar.setupWithNavController(navController)
    }
}