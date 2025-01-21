package com.example.sabresapp.ui.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.test.services.storage.file.PropertyFile.Column
import com.example.sabresapp.ui.theme.customBlue
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sabresapp.data.Player
import com.example.sabresapp.data.PlayerStats
import com.example.sabresapp.network.Repository
import com.example.sabresapp.type.SeasonTotal
import com.example.sabresapp.ui.theme.customYellow
import com.example.sabresapp.ui.viewModel.PlayerStatsViewModel

@Composable
fun PlayerPage(
    playerId: String?,
    viewModel: PlayerStatsViewModel
) {
    val playerData by viewModel.playerData.observeAsState()

    LaunchedEffect(playerId) {
        viewModel.fetchPlayerData(playerId!!)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(customBlue)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(playerData?.headshot)
                .crossfade(true)
                .build(),
            contentDescription = "${playerData?.firstName} ${playerData?.lastName}'s headshot",
            modifier = Modifier
                .size(200.dp)
                .padding(end = 16.dp)
                .border(1.dp, customYellow)
        )
        Text(text = "${playerData?.firstName} ${playerData?.lastName}",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 8.dp),
            color = Color.White
        )
        Row(
            modifier = Modifier
        ) {

            Column(
                modifier = Modifier.padding(top = 8.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = "Position: ${playerData?.positionCode}", color = Color.White)
                Text(text = "Shoots: ${playerData?.shoots}", color = Color.White)
            }
            Column(
                modifier = Modifier.padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Text(text = "Height: ${playerData?.height}", color = Color.White)
                Text(text = "Weight: ${playerData?.weight}", color = Color.White)
            }
        }
        Text(
            text = "Birthdate: ${playerData?.birthDate}",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.White
        )
        Text(
            text = "Birthplace: ${playerData?.birthCity}, ${playerData?.birthStateProvince}, ${playerData?.birthCountry}",
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White
        )
        playerData?.seasonTotals?.let { PlayerStatsTable(playerStats = it) }
    }
}

@Composable
fun PlayerStatsTable(playerStats: List<PlayerStats>) {
    // Table Headers
    val headers = listOf("Season", "Team", "GP", "G", "A", "Pts")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Header Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp, customYellow),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(headers) { header ->
                Text(
                    text = header,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .widthIn(max = 100.dp), // Set a max width if needed
                    maxLines = 1,
                    color = Color.White
                )
            }
        }

        // Player Stats Rows
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(playerStats) { stat ->
                PlayerStatsRow(stat)
            }
        }
    }
}

@Composable
fun PlayerStatsRow(stat: PlayerStats) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, customYellow),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stat.season,
                fontSize = 20.sp,
                modifier = Modifier.widthIn(max = 120.dp)
                    .padding(horizontal = 3.dp),
                color = Color.White
            )
        }
        item {
            Text(
                text = stat.team,
                fontSize = 20.sp,
                modifier = Modifier.widthIn(max = 150.dp)
                    .padding(horizontal = 3.dp),
                color = Color.White
            )
        }
        item {
            Text(
                text = stat.gamesPlayed.toString(),
                fontSize = 20.sp,
                modifier = Modifier.
                widthIn(max = 50.dp),
                color = Color.White
            )
        }
        item {
            Text(
                text = stat.goals.toString(),
                fontSize = 20.sp,
                modifier = Modifier.
                widthIn(max = 50.dp),
                color = Color.White
            )
        }
        item {
            Text(
                text = stat.assists.toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .widthIn(max = 50.dp),
                color = Color.White
            )
        }
        item {
            Text(
                text = stat.points.toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .widthIn(max = 50.dp),
                color = Color.White
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PlayerDataPreview() {
//    MaterialTheme {
//        PlayerPage(
//            playerId = "8478402",
//            viewModel = PlayerStatsViewModel(
//                repository = Repository()
//            )
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun PlayerStatsPreview() {
    MaterialTheme {
        PlayerStatsTable(
            playerStats = listOf(
                PlayerStats("2020-2021", "BUFfalo Sabres", 56, 15, 18, 33),
                PlayerStats("2019-2020", "BUF", 68, 20, 25, 45),
                PlayerStats("2018-2019", "BUF", 82, 30, 40, 70),
                PlayerStats("2017-2018", "BUF", 82, 25, 35, 60),
                PlayerStats("2016-2017", "BUF", 82, 20, 30, 50),
            )
        )
    }
}