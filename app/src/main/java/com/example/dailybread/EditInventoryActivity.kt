package com.example.dailybread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dailybread.compose.EditInventoryScreen
import com.example.dailybread.compose.InventoryScreen
import com.example.dailybread.ui.theme.DailyBreadTheme

class EditInventoryActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBreadTheme {
                EditInventoryScreen()
            }
        }
    }
}