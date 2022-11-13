package com.example.dailybread.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.data.InventoryRepository.getInventory
import com.example.dailybread.compose.MyModalDrawer
import com.example.dailybread.compose.MyTopAppBar
import com.example.dailybread.data.Recipe
import com.example.dailybread.data.RecipeRepository
import com.example.dailybread.data.RecipeRepository.id
import com.example.dailybread.data.recipes
import kotlinx.coroutines.launch


@Composable
fun RecipeScreen(navController: NavController, recipeName: String = "") {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        recipes.recipes.forEach{
            if(recipeName == it.name)
                RecipeScreen(recipe = it,openDrawer = openDrawer)
            else
                RecipeScreen(openDrawer = openDrawer)
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RecipeScreen(recipe: Recipe = RecipeRepository.getRecipe(id), openDrawer: () -> Unit) {
    var step: Int = 0
    val available = remember { mutableStateOf(false) }
    val ingredients = mutableListOf<String>()
    val inventory = getInventory()
    inventory.forEach {
        it.items.forEach {
            ingredients.add(it.name)
        }
    }

    Scaffold(topBar = {
        MyTopAppBar(title = "Recipe",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() })
    }) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))
        ) {

            LazyColumn {
                item {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = recipe.name, fontSize = 25.sp, color = Color.DarkGray)
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Ingredients\n",
                            fontSize = 20.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                        recipe.ingredients.forEach {
                            for (name in ingredients) {
                                if (it.contains(name, ignoreCase = true)) {
                                    available.value = true
                                    break
                                } else {
                                    available.value = false
                                }
                            }

                            if (available.value) {
                                Text(text = it, color = Color.DarkGray)
                            } else {
                                Text(text = it, color = Color.Red)
                            }

                        }

                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Instructions\n", fontSize = 20.sp, color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                        recipe.instructions.forEach {
                           // step++ //idk why this keeps going up
                            //Text(text = "$step. $it\n", color = Color.DarkGray)
                            Text(text = "$it\n", color = Color.DarkGray)

                        }
                    }
                }

            }
        }
    }

}
