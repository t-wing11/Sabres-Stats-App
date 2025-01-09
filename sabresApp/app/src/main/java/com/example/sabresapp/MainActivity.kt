package com.example.sabresapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sabresapp.network.Repository
import com.example.sabresapp.ui.compose.HomeScreen
import com.example.sabresapp.ui.compose.ResultsScreen
import com.example.sabresapp.ui.viewModel.RosterViewModel

class MainActivity : ComponentActivity() {
    private lateinit var rosterViewModel: RosterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        rosterViewModel = ViewModelProvider(
            this,
            RosterViewModel.Factory(repository)
        )[RosterViewModel::class.java]

        setContent{
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("result/{season}") { backStackEntry ->
                    val season = backStackEntry.arguments?.getString("season")
                    ResultsScreen(rosterViewModel, season = season)
                }
            }
        }
    }
}
