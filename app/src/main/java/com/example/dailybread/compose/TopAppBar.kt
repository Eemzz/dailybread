package com.example.dailybread.compose

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.R

@Composable
fun MyTopAppBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit){

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors( colorResource(
            R
            .color.DByellow)
        ),
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "", tint = Color.White)
            }
        },
        title = {
            Text(title, textAlign = TextAlign.Center, modifier = Modifier, color = Color
                .White, fontSize = 20.sp
            )
        },


    )


}

