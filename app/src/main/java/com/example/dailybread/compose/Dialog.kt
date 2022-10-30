package com.example.dailybread.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.InventoryRepository.addCategory
import com.example.dailybread.InventoryRepository.addIngredient
import com.example.dailybread.InventoryRepository.editIngredient
import com.example.dailybread.InventoryRepository.removeCategory
import com.example.dailybread.data.Category
import com.example.dailybread.data.Ingredient

@Composable
fun Dialog(DialogContent: @Composable BoxScope.() -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Card(
            Modifier
                .clickable { }
                //.align(Center)
                .fillMaxSize()
                .alpha(0.7f), backgroundColor = Color.White,
            elevation = 0.dp
        ) {}

        Card(
            Modifier
                .align(Center)
                .padding(20.dp, 10.dp),

            backgroundColor = Color.White,
            shape = RoundedCornerShape
                (5),
            elevation = 4.dp
        ) {
            DialogContent()
        }
    }

}

@Composable
fun DeleteCategory(openDialog: MutableState<Boolean>, category: Category) {
    Column(Modifier.padding(16.dp), horizontalAlignment = CenterHorizontally) {
        Text(text = "Are you sure you want to delete \"" + category.title + "\" category?",
            color
            = Color.DarkGray)
        Row(Modifier.padding(top = 16.dp)){
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    removeCategory(category)
                    openDialog.value = false},
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
            ) {
                Text(
                    text = "Yes", modifier = Modifier, color = Color.White,
                    fontSize
                    = 10.sp
                )

            }
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = { openDialog.value = false},
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
            ) {
                Text(
                    text = "Cancel", modifier = Modifier, color = Color.White,
                    fontSize
                    = 10.sp
                )

            }
        }

    }
}
@Composable
fun AddIngredient(openDialog: MutableState<Boolean>,category: Category){
    val addIngredientNameTextState = remember { mutableStateOf(TextFieldValue()) }
    val addIngredientCountTextState = remember { mutableStateOf(TextFieldValue()) }
    Column(Modifier.padding(16.dp), horizontalAlignment = CenterHorizontally) {
        Text(text = "Add Ingredient", color = Color.DarkGray, fontSize = 20.sp)
        DBTextField(label = "Name",
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            textState = addIngredientNameTextState
        )
        DBTextField(label = "Quantity",
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            textState = addIngredientCountTextState
        )

        Row(Modifier.padding(top = 16.dp)){
            Button(
                onClick = {
                    val newIng = Ingredient(addIngredientNameTextState.value.text, addIngredientCountTextState.value.text)
                    addIngredient(category, newIng)
                    openDialog.value = false
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                modifier = Modifier
                    .padding(5.dp)

            ) {
                Text(
                    text = "Add", modifier = Modifier, color = Color.White,
                    fontSize
                    = 15.sp
                )
            }
            Button(onClick = { openDialog.value = !openDialog.value },shape = RoundedCornerShape(50),
                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                modifier = Modifier
                    .padding(5.dp)) {
                Text(text = "Cancel",modifier = Modifier, color = Color.White,
                    fontSize
                    = 15.sp)

            }
        }

    }
}

@Composable
fun EditIngredient(openDialog: MutableState<Boolean>,category: Category ,ingredient: Ingredient) {
    val editIngredientNameTextState = remember { mutableStateOf(TextFieldValue()) }
    val editIngredientCountTextState = remember { mutableStateOf(TextFieldValue()) }
    var newName: String = ingredient.name.toString()
    var newCount: String = ingredient.count.toString()
    Column(Modifier.padding(16.dp), horizontalAlignment = CenterHorizontally) {
        Text(text = "Edit Ingredient", color = Color.DarkGray, fontSize = 20.sp)
        DBTextField(label = ingredient.name.toString(),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            textState = editIngredientNameTextState
        )
        DBTextField(label = ingredient.count.toString(),
            keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
            ),
            textState = editIngredientCountTextState
        )

        Row(Modifier.padding(top = 16.dp)){
            Button(
                onClick = {

                    if(editIngredientNameTextState.value.text.isNotBlank()){
                        newName = editIngredientNameTextState.value.text
                    }
                    if(editIngredientCountTextState.value.text.isNotBlank()){
                        newCount = editIngredientCountTextState.value.text
                    }
                    editIngredient(category,ingredient,
                        newName,
                        newCount)


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

    }

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

        Row(Modifier.padding(top = 16.dp)){
            Button(
                onClick = {
                    openDialog.value = false

                    val ing = Ingredient("", "")
                    val ingList = mutableListOf(ing)
                    val newCategory =
                        Category(NewCategoryTextState.value.text, ingList)

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
    }
}