package com.example.dailybread.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.data.InventoryRepository
import com.example.dailybread.R
import com.example.dailybread.compose.MyModalDrawer
import com.example.dailybread.compose.MyTopAppBar
import com.example.dailybread.data.Category
import com.example.dailybread.data.Ingredient
import kotlinx.coroutines.launch


@Composable
fun InventoryScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        val inventory = InventoryRepository.getInventory()
        InventoryScreen(inventory, openDrawer = openDrawer, navController = navController)
    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InventoryScreen(
    categories: MutableList<Category>,
    openDrawer: () -> Unit, navController: NavController) {
    Scaffold(topBar = {
        MyTopAppBar(title = "Inventory",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() })
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("edit")
                },
                backgroundColor = colorResource(
                    R.color.DByellow
                ),
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Filled.Edit,
                    ""
                )
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))
        ) {
            LazyColumn(
                Modifier.align(Alignment.TopCenter).padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)

            ) {
                item {
                    if(InventoryRepository.isUnSaved){
                        Text(text = "You have unsaved changes to your inventory.", color = Color.Red,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .clickable{navController.navigate("edit")})
                    }
                }
                items(categories) { item ->
                   InventoryCard(item)
                }
            }
        }

    }
}
@Composable
fun InventoryCard(category: Category) {
    Card(
        Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape
            (10.dp)
    ) {


        Column(Modifier.padding( PaddingValues(16.dp))) {

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = "\n" + category.title + "\n",
                fontSize = 20.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            category.items.forEach { ingredient ->
                Text(
                    ingredient.name + ", " + ingredient.count, color
                    = Color.DarkGray
                )
            }


        }

    }
}


