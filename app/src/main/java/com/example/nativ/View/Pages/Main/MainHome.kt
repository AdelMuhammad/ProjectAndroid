package com.example.nativ.View.Pages.Main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.nativ.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainHome : AppCompatActivity() {
    private lateinit var bottomNavigationView: ChipNavigationBar
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_home)
        initializeViews()
        setupWindowInsets()
        setupNavigation()
    }
    private fun initializeViews() {
        bottomNavigationView = findViewById(R.id.bottom_nav)
        navController = findNavController(R.id.fragmentContainerView2)
    }
    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun setupNavigation() {
        bottomNavigationView.setOnItemSelectedListener { id ->
            navController.navigate(id)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.setItemSelected(destination.id, true)
        }
    }
}