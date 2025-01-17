package com.example.sabresapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sabresapp.data.Player
import com.example.sabresapp.ui.theme.customBlue
import com.example.sabresapp.ui.theme.customYellow
import com.example.sabresapp.ui.viewModel.RosterViewModel

@Composable
fun ResultsScreen(
    rosterViewModel: RosterViewModel,
    season: String?,
    navController: NavController
) {
    val rosterData by rosterViewModel.rosterData.observeAsState()

    LaunchedEffect(season) {
        rosterViewModel.fetchRoster(season = season!!)
    }
    Column(modifier = Modifier.fillMaxWidth()
        .background(customBlue)) {
        if (rosterData != null) {
            Box(modifier = Modifier
                .fillMaxWidth()
            .border(1.dp, customYellow)) {
                Column {
                    Text(text = rosterData!!.name, fontSize = 30.sp, color = Color.White)
                    Text(text = "Season: ${rosterData!!.season}", fontSize = 20.sp, color = Color.White)
                }

            }

            LazyColumn {
                items(rosterData!!.players) { player ->
                    PlayerInfo(
                        playerData = player,
                        onPlayerClick = {
                            navController.navigate("player/${player.id}") {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

        } else {
            Text(text = "Loading or no data available")
        }
    }

}

@Composable
fun PlayerInfo(
   playerData: Player,
   onPlayerClick: () -> Unit = {}
) {

    Box(modifier = Modifier
        .background(customBlue)
        .fillMaxWidth()
        .border(1.dp, customYellow)
        .clickable { onPlayerClick() }
    ) {
        Row(
            modifier = Modifier
                .height(100.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(playerData.headshot)
                    .crossfade(true)
                    .build(),
                contentDescription = "${playerData.firstName} ${playerData.lastName}'s headshot",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "${playerData.firstName} ${playerData.lastName}", fontSize = 20.sp,  modifier = Modifier.padding(bottom = 8.dp), color = Color.White)
                Text(text = "Position: ${playerData.positionCode}", fontSize = 15.sp, modifier = Modifier.padding(bottom = 8.dp), color = Color.White)
                Text(text = "Number: ${playerData.sweaterNumber}", fontSize = 15.sp, modifier = Modifier.padding(bottom = 8.dp), color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerInfoPreview() {
    MaterialTheme {
        PlayerInfo(
            playerData = Player(
                id = "8482061",
                firstName = "Brandon",
                lastName = "Biro",
                positionCode = "L",
                headshot = "https://assets.nhle.com/mugs/nhl/20212022/BUF/8482061.png",
                sweaterNumber = 52,
                shoots = "L",
                height = 72.toString(),
                weight = 165,
                birthDate = "1998-03-11",
                birthCity = "Sherwood Park",
                birthStateProvince = "AB",
                birthCountry = "CAN"
            ),
            onPlayerClick = { }
        )
    }
}