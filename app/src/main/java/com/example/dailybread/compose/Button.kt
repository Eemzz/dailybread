package com.example.dailybread.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
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
fun DBButton(ToastText: String, btnText: String, context: Context) {
    Box(
        Modifier
        // .absolutePadding(80.dp, 10.dp, 20.dp, 40.dp)
    ) {

        Button(onClick = { Toast.makeText(context, ToastText, Toast.LENGTH_SHORT).show() },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
        ) {
            Text(text = btnText, modifier = Modifier, color = Color.White, fontSize = 15.sp)
        }
    }

}

@Composable
fun MultiFloatingActionButton() {

}