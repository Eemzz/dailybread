package com.example.dailybread.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.R

@Composable
fun DBButton(btnText: String, modifier: Modifier = Modifier
    .width(120.dp)
    .height(50.dp),onclick: () -> Unit
) {
    Box{
        Button(
            onClick = onclick,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
            modifier = modifier

        ) {
            Text(text = btnText, color = Color.White, fontSize = 15.sp)
        }
    }

}

@Composable
fun Loading() {
    Box(Modifier.fillMaxSize().background(Color.White.copy(alpha = 0.75f)).clickable(
        MutableInteractionSource(), indication = null) {}) {
        CircularProgressIndicator(modifier = Modifier.align(Center))
    }
}

