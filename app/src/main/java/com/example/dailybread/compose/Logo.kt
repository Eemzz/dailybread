package com.example.dailybread.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DBLogo(name: String) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color(0xFFFDAF01)
        ),

        modifier = Modifier
            .width(300.dp)
            .height(140.dp)


    ) {
        Text(text = name, modifier = Modifier, color = Color.White, fontSize = 50.sp)

    }
}