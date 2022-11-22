package com.example.dailybread

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dailybread.compose.MyAppNavHost
import com.example.dailybread.data.InventoryRepository
//import com.example.dailybread.user.isOnline
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.ui.theme.DailyBreadTheme
import com.example.dailybread.user.UserManager
import com.example.dailybread.user.isOnline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            //TODO get inventory from api only fetch from disk if no internet or call fails
          //  if(!isOnline(context = this@MainActivity)){
                //InventoryRepository.setInventory(InventoryStore.readInventory(this@MainActivity).ingList)
                //InventoryRepository.setInventory(UserManager.useremail)
          //  }else{
                //TODO get inventory from api call
           // }

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