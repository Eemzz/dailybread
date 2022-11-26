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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.R
import com.example.dailybread.compose.*
import com.example.dailybread.data.*
import com.example.dailybread.data.InventoryRepository.isUnSaved
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.user.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class EditDelete (val ingredient: Ingredient, val edit: Boolean, val delete: Boolean)
data class InventoryEdit (val edit: MutableList<EditDelete>)

object EditInventory {
    var editDetails = InventoryEdit(mutableListOf())
}

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
        val inventory = InventoryRepository.getInventory()
        EditInventoryScreen(inventory, openDrawer = openDrawer, navController = navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditInventoryScreen(
    categories: MutableList<Category>,
    openDrawer: () -> Unit, navController: NavController
) {
    val openAddCategoryDialog = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var position: FabPosition = FabPosition.End
    val move = remember { mutableStateOf(false) }

   if(!move.value){
       position = FabPosition.End
    }
    else{
       position =  FabPosition.Center

    }

    Scaffold(topBar = {
        MyTopAppBar(title = "Edit Inventory",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() })
    },floatingActionButtonPosition = position,
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    //TODO make api call to save Inventory
                    InventoryRepository.isUnSaved = false
                    println("is unsaved?" + isUnSaved)
                    scope.launch(Dispatchers.Main) {
                        //InventoryStore.writeInventory(context, Inventory(categories))
                        withContext(Dispatchers.IO) {
                            InventoryRepository.setInventory(UserManager.useremail)
                        }

                        navController.navigate("inventory")
                    }
                },
                backgroundColor = colorResource(R.color.DByellow),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Save, "")
            }

        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))
        ) {
            LazyColumn(
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp) ) {
                item {
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxSize(),
                        onClick = { openAddCategoryDialog.value = !openAddCategoryDialog.value },
                        shape = RoundedCornerShape(15),
                        colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    ) {
                        Text(
                            text = "New Category", modifier = Modifier, color = Color.White,
                            fontSize
                            = 10.sp
                        )
                    }

                }
                items(categories) { item ->
                    LaunchedEffect(true){
                        move.value = false
                    }

                    EditInventoryCard(item)
                }
                item {

                    LaunchedEffect(true) {
                        move.value = true
                    }

                }
            }
            if (openAddCategoryDialog.value) {
                AddCategoryDialog(openAddCategoryDialog, categories)
            }

        }

    }

}

@Composable
fun EditInventoryCard(category: Category) {
    val openEditDialog = remember { mutableStateOf(false) }
    val openDeleteIngDialog = remember{ mutableStateOf(false) }
    val openAddDialog = remember { mutableStateOf(false) }
    val openDeleteDialog = remember { mutableStateOf(false) }

    Card(
        Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape
            (10.dp)
    ) {

        Column(Modifier.padding(PaddingValues(16.dp))) {

            Text(
                modifier = Modifier.align(CenterHorizontally),
                text = "\n" + category.title + "\n",
                fontSize = 20.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )


            Row(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp), horizontalArrangement =
                Arrangement.Center,
                CenterVertically
            ) {
                Button(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = { openAddDialog.value = !openAddDialog.value },
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
                    onClick = { openDeleteDialog.value = !openDeleteDialog.value },
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



            if (openAddDialog.value) {
                AddIngredientDialog(openDialog = openAddDialog, category = category)

            }
            if (openDeleteDialog.value) {
                DeleteCategoryDialog(openDeleteDialog, category)
            }
            println("category items: " + category.items)
            category.items.forEach { ingredient ->
                IngredientRow(ingredient, category)//, openEditDialog)//, openDeleteIngDialog)
                /*if (openEditDialog.value) {
                    println("edit this ingredient: " + ingredient.name)
                    EditIngredientDialog(openEditDialog, category, ingredient)

                }*/
            }
        }
    }

}


@Composable
fun IngredientRow(
    ingredient: Ingredient,
    category: Category,
    openEditDialog: MutableState<Boolean> = remember { mutableStateOf(false) },
   // openDeleteIngDialog: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    //val openEdit = remember { mutableListOf<String>() }
    //val openDeleteIng = remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp),
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
                        /*openEditDialog.add(ingredient.name)
                        openEditDialog.add(true.toString())*/
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
                        InventoryRepository.tempDeleteIngredient(category, ingredient)
                        //openDeleteIngDialog.value = !openDeleteIngDialog.value
                        //DeleteIngredientDialog(openDialog = openDeleteDialog, category = category, ingredient = ingredient)
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
    if (openEditDialog.value) {
        EditIngredientDialog(openEditDialog, category, ingredient)
    }

}
