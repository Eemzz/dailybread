package com.example.dailybread


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dailybread.compose.*
import com.example.dailybread.data.mockItems
import com.example.dailybread.ui.theme.DailyBreadTheme
import kotlinx.coroutines.launch


class HomeActivity: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            InventoryRepository.setInventory.emit(mockItems)
        }
        setContent {
            DailyBreadTheme {
                val navController = rememberNavController()
                MyAppNavHost(navController = navController) }
            }
    }



//test
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DailyBreadTheme {

        }
    }
}