package com.example.dailybread.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.InventoryRepository
import com.example.dailybread.R
import com.example.dailybread.compose.*
import com.example.dailybread.data.Category
import com.example.dailybread.data.Ingredient
import com.example.dailybread.data.mockItems
import kotlinx.coroutines.launch

@Composable
fun EditInventoryScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
//        val inventory = InventoryRepository.getInventory()
        val inventory = mockItems.toMutableStateList()
        EditInventoryScreen(inventory, openDrawer = openDrawer, navController = navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditInventoryScreen(categories: SnapshotStateList<Category>,
                        openDrawer: () -> Unit, navController: NavController) {
    val openAddCategoryDialog = remember { mutableStateOf(false) }
    Scaffold(topBar = {
        MyTopAppBar(title = "Edit Inventory",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() }) },
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    openAddCategoryDialog.value = !openAddCategoryDialog.value

                },
                backgroundColor = colorResource(R.color.DByellow),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))
        ) {
            LazyColumn(Modifier.align(Center)) {
                items(categories) { item ->
                    EditInventoryCard(categories, item)
                }
            }
            if (openAddCategoryDialog.value) {
                AddCategoryDialog(openAddCategoryDialog)
            }

//            if (openEditDialog.value) {
//                Dialog() {
//                    EditIngredient(openEditDialog, ingredient, )
//                }
//            }
        }

    }

}
@Composable
fun EditInventoryCard(categories: SnapshotStateList<Category>, category: Category) {
    val openEditDialog = remember { mutableStateOf(false) }
    val openAddDialog = remember { mutableStateOf(false) }
    val openDeleteDialog = remember { mutableStateOf(false) }
    Card(
        Modifier
            .padding(20.dp, 10.dp)
            .width(300.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape
            (5)
    ) {

        Column(Modifier.padding( PaddingValues(16.dp))) {
            Column(
                Modifier.fillMaxSize(), horizontalAlignment =
                Alignment.CenterHorizontally,
                //CenterVertically
            ) {
                Text(
                    text = "\n" + category.title + "\n",
                    fontSize = 20.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

            }
            Row(Modifier.fillMaxSize().padding(bottom = 16.dp), horizontalArrangement =
            Arrangement.Center,
                CenterVertically){
                Button(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { openAddDialog.value = !openAddDialog.value},
                    shape = RoundedCornerShape(15),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    ) {
                    Text(
                        text = "New Ingredient", modifier = Modifier, color = Color.White,
                        fontSize
                        = 10.sp
                    )
                }
                Button(
                    onClick = { openDeleteDialog.value = !openDeleteDialog.value},
                    shape = RoundedCornerShape(15),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                ) {
                    Text(
                        text = "Delete Category", modifier = Modifier, color = Color.White,
                        fontSize
                        = 10.sp
                    )

                }
            }



            if(openAddDialog.value){
                AddIngredientDialog(openDialog = openAddDialog, category = category)

            }
            if(openDeleteDialog.value){
                DeleteCategoryDialog(openDialog = openDeleteDialog, category = category)
            }
            category.items.forEach { ingredient ->
                IngredientRow(ingredient, categories, category, openEditDialog)
                if (openEditDialog.value) {
                    EditIngredientDialog(openEditDialog, category,ingredient)

                }

            }
        }
    }

//


}



@Composable
fun IngredientRow(
    ingredient: Ingredient,
    categories: SnapshotStateList<Category>,
    category: Category,
    openEditDialog: MutableState<Boolean> = remember { mutableStateOf(false) }
) {

        Row(
            Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
            ,
            verticalAlignment = CenterVertically
        ) {

            if (ingredient.name != "") {
                Text(
                    ingredient.name + ", " + ingredient.count, color
                    = Color.DarkGray
                )
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            openEditDialog.value = !openEditDialog.value

                        }, Modifier
                            .padding(end = 14.dp)
                            .size(25.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            "",
                            tint = Color.DarkGray
                        )
                    }
                    IconButton(
                        onClick = {
                            val newItem = category.copy(items = category.items.toMutableList().apply { remove(ingredient) })
                            val index = categories.indexOf(category)
                            categories[index] = newItem
                            InventoryRepository.deleteIngredient(category, ingredient)
                        }, Modifier.size
                            (25.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            "",
                            tint = Color.DarkGray
                        )
                    }
                }
            }


        }

}
