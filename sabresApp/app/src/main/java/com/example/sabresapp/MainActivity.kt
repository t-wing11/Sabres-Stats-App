package com.example.sabresapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sabresapp.data.Player
import com.example.sabresapp.network.Repository
import com.example.sabresapp.ui.compose.HomeScreen
import com.example.sabresapp.ui.compose.PlayerPage
import com.example.sabresapp.ui.compose.ResultsScreen
import com.example.sabresapp.ui.theme.SabresAppTheme
import com.example.sabresapp.ui.viewModel.PlayerStatsViewModel
import com.example.sabresapp.ui.viewModel.RosterViewModel

class MainActivity : ComponentActivity() {
    private lateinit var rosterViewModel: RosterViewModel
    private lateinit var playerViewModel: PlayerStatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        rosterViewModel = ViewModelProvider(
            this,
            RosterViewModel.Factory(repository)
        )[RosterViewModel::class.java]

        playerViewModel = ViewModelProvider(
            this,
            PlayerStatsViewModel.Factory(repository)
        )[PlayerStatsViewModel::class.java]

        setContent{
            SabresAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController = navController)
                    }
                    composable("result/{season}")
                    { backStackEntry ->
                        val season = backStackEntry.arguments?.getString("season")
                        ResultsScreen(
                            rosterViewModel = rosterViewModel,
                            season = season,
                            navController = navController
                        )
                    }
                    composable("player/{playerId}") { backStackEntry ->
                        val playerId = backStackEntry.arguments?.getString("playerId")
                        PlayerPage(playerId = playerId, viewModel = playerViewModel)
                    }
                }
            }
        }
    }
}
