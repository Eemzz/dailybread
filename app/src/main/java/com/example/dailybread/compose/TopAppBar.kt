package com.example.dailybread.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.R

@Composable
fun MyTopAppBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors( colorResource(
            R
            .color.DByellow)
        ),
        navigationIcon =  {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "", tint = Color.White)

            }
        },
        title = {
            Text("DailyBread", textAlign = TextAlign.Center, modifier = Modifier, color = Color
                .White, fontSize = 20.sp
            )
        },

        // backgroundColor = Color(0xFFFDAF01),
        // contentColor = Color.White

    )
}