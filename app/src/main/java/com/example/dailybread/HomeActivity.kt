package com.example.dailybread


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.dailybread.compose.*
import com.example.dailybread.data.RecipeManager.getRecipes
import com.example.dailybread.data.mockItems
//import com.example.dailybread.recipes.RecipeListViewModel
import com.example.dailybread.ui.theme.DailyBreadTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//import javax.inject.Inject


class HomeActivity: ComponentActivity() {

//    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    private val recipeListViewModel: RecipeListViewModel by viewModels {
//        viewModelFactory
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            InventoryRepository.setInventory.emit(mockItems)
            withContext(
                Dispatchers.IO
            ) {
                getRecipes()}
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