package com.example.sabresapp.ui.compose

import androidx.compose.foundation.background
import com.example.sabresapp.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sabresapp.ui.theme.customYellow


@Composable
fun DropDown(onItemSelected: (String) -> Unit) {

    val context = LocalContext.current
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }
    val items = context.resources.getStringArray(R.array.seasons)

    Column(
        modifier = Modifier.background(customYellow),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable { isDropDownExpanded.value = true }
        ) {
            Text(text = "Select Season", color = Color.White, fontSize = 15.sp)
        }
        DropdownMenu(
            modifier = Modifier.background(customYellow),
            expanded = isDropDownExpanded.value,
            onDismissRequest = { isDropDownExpanded.value = false }
        ) {
            items.forEachIndexed { index, season ->
                DropdownMenuItem(
                    text = {
                    Text(text = season, color = Color.White, fontSize = 15.sp)
                },
                    onClick = {
                        isDropDownExpanded.value = false
                        itemPosition.intValue = index
                        onItemSelected(season)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownDemoPreview() {
    MaterialTheme {
        DropDown(onItemSelected = {})
    }
}