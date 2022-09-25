package com.example.dailybread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dailybread.compose.InventoryScreen
import com.example.dailybread.ui.theme.DailyBreadTheme

class InventoryActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBreadTheme {
                InventoryScreen()
            }
        }
    }
//    @Preview(showBackground = true)
//    @Composable
//    fun DefaultPreview() {
//        DailyBreadTheme {
////
//        }
//    }
}