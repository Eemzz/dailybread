package com.example.dailybread.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.dailybread.data.*


@Composable
fun RecipeListScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        RecipeListScreen(recipes.recipes, openDrawer, navController)

    }
}

@Composable
fun RecipeListScreen(
    recipes: List<Recipe>, openDrawer: () -> Unit,
    navController: NavController,
) {
    val openDialog = remember { mutableStateOf(false) }
    var selectedRecipe: Recipe = spaghetti
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
                item() {
                    recipes.forEach {
                        Card(
                            Modifier
                                .padding(20.dp, 10.dp)
                                .width(300.dp)
                                .clickable {
                                    //TODO ideally this should navigate to recipe screen with recipe as param
                                    openDialog.value = true
                                    selectedRecipe = it
                                }
                                .fillMaxHeight(),
                            shape = RoundedCornerShape
                                (5)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = CenterHorizontally
                            ) {
                                Text(it.name, color = Color.DarkGray, fontSize = 20.sp)
                            }
                            
                        }


                    }


                }


            }

        }
        if(openDialog.value){
            DisplayRecipe(recipe = selectedRecipe)
        }

    }

}

@Composable
fun DisplayRecipe(recipe: Recipe){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFCF7EC))
    ) {
        LazyColumn(){
            item {
                Column() {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = recipe.name, fontSize = 25.sp, color = Color.DarkGray)
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Ingredients",
                            fontSize = 20.sp,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                        recipe.ingredients.forEach {
                            Text(text = it, color = Color.DarkGray)
                        }
//                        Text(text = recipe.ingredients.toString())
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Instructions", fontSize = 20.sp, color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                        recipe.instructions.forEach {
                            Text(text = it, color = Color.DarkGray)
                        }
                    }
                }
            }
        }

    }
}




