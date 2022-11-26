package com.example.dailybread.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.dailybread.compose.MyModalDrawer
import com.example.dailybread.compose.MyTopAppBar
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
        RecipeListScreen(RecipeRepository.recipes.values.toList(), openDrawer, navController)


    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeListScreen(
    recipes: List<Recipe>, openDrawer: () -> Unit,
    navController: NavController,
) {
    val openDialog = remember { mutableStateOf(false) }

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
            if(recipes.isEmpty()){
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(16.dp), horizontalAlignment = CenterHorizontally) {
                    Text(text = "Could not generate a recipe with your current inventory", color = Color.DarkGray)
                }
            }else{
                LazyColumn (Modifier.align(Alignment.Center)) {
                    item() {
                        recipes.forEach {
                            Card(
                                Modifier
                                    .padding(20.dp, 10.dp)
                                    .width(300.dp)
                                    .clickable {

                                        RecipeRepository.id = it.id
                                        navController.navigate("recipe")

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


        }


    }

}





