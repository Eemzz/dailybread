package com.example.dailybread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dailybread.compose.RecipeScreen
import com.example.dailybread.ui.theme.DailyBreadTheme

class RecipeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBreadTheme {
                RecipeScreen()

            }
        }
    }
}