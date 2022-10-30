package com.example.dailybread.compose

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.data.Recipe
import com.example.dailybread.data.spaghetti
import kotlinx.coroutines.launch

@Composable
fun RecipeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        RecipeScreen(openDrawer = openDrawer)
    }
}

@Composable
fun RecipeScreen(recipe: Recipe = spaghetti, openDrawer: () -> Unit) {
    val context = LocalContext.current
    Scaffold(topBar = {MyTopAppBar(title = "Recipe",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() })}) {
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