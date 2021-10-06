package com.example.savedstatehandle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (supportFragmentManager.findFragmentById(R.id.calcNavigation)
                as NavHostFragment).navController.apply {
            navController = this
        }
        navController.setGraph(R.navigation.navigation_calculation)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                Destination.RESULT.id -> supportActionBar?.title = "Fragment A"
                Destination.CALCULATIONS.id -> supportActionBar?.title = "Fragment B"
            }
        }
    }

    enum class Destination(val id: Int) {
        RESULT(R.id.results),
        CALCULATIONS(R.id.calculations)
    }

    companion object {
        const val TYPE = "type"
        const val RESULT = "result"
    }
}