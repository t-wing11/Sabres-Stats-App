package com.example.sabresapp.ui.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sabresapp.ui.theme.customBlue
import com.example.sabresapp.ui.theme.customYellow

@Composable
fun HomeScreen(navController: NavController) {

    val selectedSeason = remember { mutableStateOf("19701971") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp))
        {
            Text(text = "Sabres through the Seasons", fontSize = 30.sp, color = Color.White)
            Text(text = "Select a season to view results", fontSize = 20.sp,  color = Color.White)
            DropDown(onItemSelected = { season ->
                selectedSeason.value = season
            })
            Button(
                modifier = Modifier,
                shape = RectangleShape,
                onClick = {
                    Log.d("tag", "selected season: ${selectedSeason.value}")
                    navController.navigate("result/${selectedSeason.value}")
                },
                colors = ButtonColors(
                    contentColor = Color.White, containerColor = customYellow,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                )
            )
            {
                Text(text = "View ${selectedSeason.value} Roster")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenDemo() {
    MaterialTheme {//fake the nav controller
        HomeScreen(navController = NavController(LocalContext.current))
    }
}