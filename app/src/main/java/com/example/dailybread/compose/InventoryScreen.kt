package com.example.dailybread.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.InventoryRepository
import com.example.dailybread.R
import com.example.dailybread.data.Category
import com.example.dailybread.data.mockItems
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
    val inventory = InventoryRepository.getInventory().collectAsState(initial = mockItems)
    MyModalDrawer(drawerState, navController) {
        InventoryScreen(openDrawer = openDrawer, navController = navController, categories = inventory.value)
    }
}

@Composable
fun InventoryScreen(
    categories: List<Category> = mockItems,
    openDrawer: () -> Unit,
    navController: NavController) {
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
                Modifier.align(
                    Center
                )
            ) {
                items(categories) { item ->
                    Card(
                        Modifier
                            .padding(20.dp, 10.dp)
                            .size(300.dp), shape = RoundedCornerShape
                            (5)
                    ) {


                        LazyColumn(contentPadding = PaddingValues(16.dp)) {
                            item {

                                Column(
                                    Modifier.fillMaxSize(),
                                    horizontalAlignment =
                                    CenterHorizontally
                                ) {
                                    Text(
                                        text = "\n" + item.title + "\n",
                                        fontSize = 20.sp,
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                item.items.forEach { ingredient ->
                                    Column {
                                        if (ingredient.name != "") {
                                            Text(
                                                ingredient.name + ", " + ingredient.count, color
                                                = Color.DarkGray
                                            )
                                        }
                                    }
                                }


                            }


                        }


                    }
                }
            }
        }

    }
}