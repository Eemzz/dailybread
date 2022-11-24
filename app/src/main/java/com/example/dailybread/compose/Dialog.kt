package com.example.dailybread.compose


import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.data.Category
import com.example.dailybread.data.Ingredient
import com.example.dailybread.data.InventoryRepository.addCategory
import com.example.dailybread.data.InventoryRepository.addIngredient
import com.example.dailybread.data.InventoryRepository.editIngredient
import com.example.dailybread.data.InventoryRepository.removeCategory
import com.example.dailybread.data.Recipe
import com.example.dailybread.R
import com.example.dailybread.data.InventoryRepository
import com.example.dailybread.data.InventoryRepository.checkIngredient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun DisplayRecipe(openDialog: MutableState<Boolean>, recipe: Recipe){
    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },

        text = {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                item{
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


        },
        buttons = {

        }
    )

}

@Composable
fun ChangePasswordDialog(openDialog: MutableState<Boolean>){
    val NewCategoryTextState = remember { mutableStateOf(TextFieldValue()) }
    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },

        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Change Password", color = Color.DarkGray, fontSize = 20.sp)
                DBTextField(

                    "Current Password",
                    KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    NewCategoryTextState
                )
                DBTextField(

                    "New Password",
                    KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    NewCategoryTextState
                )

            }

        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {


                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)

                ) {
                    Text(
                        text = "Confirm", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
                Button(onClick = { openDialog.value = !openDialog.value },shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)) {
                    Text(text = "Cancel",modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp)

                }
            }

        },
    )
}

@Composable
fun DeleteCategoryDialog(
    openDialog: MutableState<Boolean>,
    category: Category
) {

    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Delete Category", color
                    = Color.DarkGray, fontSize = 20.sp
                )
            }
        },
        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Are you sure you want to delete \"" + category.title + "\" category?",
                    color
                    = Color.DarkGray
                )
            }


        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp),
                    onClick = {
                        removeCategory(category)
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01))
                ) {
                    Text(
                        text = "Yes", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp),
                    onClick = {
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                ) {
                    Text(
                        text = "Cancel", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
            }

        },

        )


}

@Composable
fun AddIngredientDialog(openDialog: MutableState<Boolean>, category: Category) {
    val addIngredientNameTextState = remember { mutableStateOf(TextFieldValue()) }
    val addIngredientCountTextState = remember { mutableStateOf(TextFieldValue()) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val added = remember { mutableStateOf(false) }

    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },

        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add Ingredient", color
                    = Color.DarkGray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp)
                )

                DBTextField(
                    label = "Name",
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textState = addIngredientNameTextState
                )
                DBTextField(
                    label = "Quantity",
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textState = addIngredientCountTextState
                )

            }

        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        val newIng = Ingredient(
                            addIngredientNameTextState.value.text,
                            addIngredientCountTextState.value.text
                        )
                        //val added =
                        scope.launch(Dispatchers.Main) {
                            withContext(Dispatchers.IO) {
                                added.value = checkIngredient(category, newIng)
                            }

                            println("added? " + added.value.toString())
                            /*if (addIngredient(category, newIng))
                            {
                                openDialog.value = false
                            }
                            else {
                                Toast.makeText(context, InventoryRepository.errorMessage, Toast.LENGTH_LONG)
                            }*/
                            openDialog.value = false
                            if (!added.value)
                            {
                                val message = "Item is already in inventory! Edit item instead."
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                            }
                        }


                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(120.dp)
                        .height(50.dp)

                ) {
                    Text(
                        text = "Add", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
                Button(
                    onClick = { openDialog.value = !openDialog.value },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .padding(5.dp)
                        .width(120.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Cancel", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )

                }
            }

        },

    )

}

@Composable
fun EditIngredientDialog(
    openDialog: MutableState<Boolean>,
    category: Category, ingredient: Ingredient
) {
    val editIngredientNameTextState = remember { mutableStateOf(TextFieldValue()) }
    val editIngredientCountTextState = remember { mutableStateOf(TextFieldValue()) }
    var newName: String = ingredient.name.toString()
    var newCount: String = ingredient.count.toString()

    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },

        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Edit Ingredient", color
                    = Color.DarkGray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp)
                )

                DBTextField(
                    label = ingredient.name.toString(),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textState = editIngredientNameTextState
                )
                DBTextField(
                    label = ingredient.count.toString(),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    textState = editIngredientCountTextState
                )

            }

        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                        if (editIngredientNameTextState.value.text.isNotBlank()) {
                            newName = editIngredientNameTextState.value.text
                        }
                        if (editIngredientCountTextState.value.text.isNotBlank()) {
                            newCount = editIngredientCountTextState.value.text
                        }
                        editIngredient(
                            category, ingredient,
                            newName,
                            newCount
                        )


                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)

                ) {
                    Text(
                        text = "Update", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
                Button(
                    onClick = { openDialog.value = !openDialog.value },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Cancel", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )

                }
            }

        },
    )

}

@Composable
fun AddCategoryDialog(openDialog: MutableState<Boolean>, categories: MutableList<Category>){
    val NewCategoryTextState = remember { mutableStateOf(TextFieldValue()) }
    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },

        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Add Category", color
                    = Color.DarkGray, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp)
                )

                DBTextField(

                    "Protein, Vegetables, Fruit, etc",
                    KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    NewCategoryTextState
                )

            }

        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        openDialog.value = false
                        val newCategory =
                            Category(NewCategoryTextState.value.text, mutableListOf())

                        //it was adding twice
                       // categories.add(newCategory)

                        addCategory(newCategory)
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)

                ) {
                    Text(
                        text = "Add", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
                Button(
                    onClick = { openDialog.value = !openDialog.value }, shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Cancel", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )

                }
            }

        },
    )
}

@Composable
fun AddCategoryContent(openDialog: MutableState<Boolean>) {
    val NewCategoryTextState = remember { mutableStateOf(TextFieldValue()) }
    Column(Modifier.padding(16.dp), horizontalAlignment = CenterHorizontally) {
        Text(text = "Add New Category", color = Color.DarkGray, fontSize = 20.sp)
        DBTextField(

            "Protein, Vegetables, Fruit, etc",
            KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            NewCategoryTextState
        )

        Row(Modifier.padding(top = 16.dp)) {
            Button(
                onClick = {
                    openDialog.value = false

                    val newCategory =
                        Category(NewCategoryTextState.value.text, mutableListOf())

                    addCategory(newCategory)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
                    .padding(5.dp)

            ) {
                Text(
                    text = "Add", modifier = Modifier, color = Color.White,
                    fontSize
                    = 15.sp
                )
            }
            Button(
                onClick = { openDialog.value = !openDialog.value }, shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
                    .padding(5.dp)
            ) {
                Text(
                    text = "Cancel", modifier = Modifier, color = Color.White,
                    fontSize
                    = 15.sp
                )

            }
        }
    }
}

@Composable
fun ErrorMessageDialog(openDialog: MutableState<Boolean>, message: String) {

    AlertDialog(
        shape = RoundedCornerShape
            (5),
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message, color
                    = Color.DarkGray, fontSize = 20.sp
                )
            }
        },
        text = {
            Column(
                Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    color
                    = Color.DarkGray
                )
            }


        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(5.dp),
                    onClick = {
                        openDialog.value = false
                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                ) {
                    Text(
                        text = "Ok", modifier = Modifier, color = Color.White,
                        fontSize
                        = 15.sp
                    )
                }
            }

        },

        )


}