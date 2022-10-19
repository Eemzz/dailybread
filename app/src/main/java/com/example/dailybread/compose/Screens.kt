package com.example.dailybread.compose

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.dailybread.EditInventoryActivity
import com.example.dailybread.InventoryActivity
import com.example.dailybread.R
import com.example.dailybread.data.*


@Composable
fun HomeScreen(){
    val context = LocalContext.current
    Scaffold(topBar = { MyTopAppBar() }) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.homebg3),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(180f),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn(){
                item {
                    Column(Modifier.fillMaxSize(),horizontalAlignment = Alignment
                    .CenterHorizontally) {
                        Card( backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                        RoundedCornerShape(10),modifier =
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                            ){



                            Column(Modifier.padding(top = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(text = "Recipe Finder", fontSize = 25.sp, color = Color.DarkGray,)

                                Column() {
                                    val ingListTextState = remember { mutableStateOf(TextFieldValue()) }
                                    DBTextField(
                                        "Tomatoes, basil, ground beef, etc",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Text,
                                            imeAction = ImeAction.Next
                                        ),
                                        ingListTextState
                                    )
                                }
                                Column(Modifier.padding(top =10.dp, bottom = 20.dp)) {
                                    DBButton(
                                        ToastText = "Looking for recipes",
                                        btnText =
                                        "Search",
                                        context = context,
                                        intent = null
                                    )
                                }
                            }


                        }
                        Card( backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                        RoundedCornerShape(10),modifier =
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                        ){
                            Column(Modifier.padding(20.dp),horizontalAlignment =
                            Alignment
                                .CenterHorizontally
                            ) {
                                Text(text = "Recipe Generator", fontSize = 25.sp, color = Color.DarkGray)
                                Column(Modifier.padding(10.dp)) {
                                    DBButton(
                                        ToastText = "Generating Recipe", btnText = "Generate",
                                        context = context, intent = null
                                    )
                                }
                                Column(Modifier.padding(vertical = 10.dp)

                                ) {


                                    Text(text = "Generates a recipe from your inventory",
                                        fontSize = 14
                                            .sp, color =
                                        Color
                                            .DarkGray)

                                }
                            }

                        }
                        Card(backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                        RoundedCornerShape(20),modifier =
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                            .clickable {
                                val intent =
                                    Intent(context, InventoryActivity::class.java)
                                startActivity(context, intent, null)
                            }
                            ) {
                            Column(Modifier.padding(vertical = 20.dp),horizontalAlignment =
                            Alignment
                                .CenterHorizontally) {
                                Text("View Inventory" , color = Color.DarkGray, fontSize = 25.sp)
                            }

                        }

                    }
                }

            }



        }
    }
}

@Composable
fun SignUpScreen() {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.appbg1),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxSize()
                .rotate(180f),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn(){
            item{
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),horizontalAlignment = Alignment
                        .CenterHorizontally){
                    DBLogo(name = "DailyBread")
                    Column(
                        Modifier
                            .fillMaxSize()
                            .align(CenterHorizontally)){
                        Card(backgroundColor = Color.White.copy(alpha = 0.75f),
                            shape = RoundedCornerShape(5),
                            modifier = Modifier
                                .padding(20.dp)
                                .align(CenterHorizontally)
                        ) {
                            Column(horizontalAlignment = CenterHorizontally) {
                                Column(Modifier.padding(20.dp)) {
                                    val nameTextState = remember { mutableStateOf(TextFieldValue()) }
                                    val emailTextState = remember { mutableStateOf(TextFieldValue()) }
                                    val confirmTextState = remember { mutableStateOf(TextFieldValue()) }
                                    val passwordTextState = remember { mutableStateOf(TextFieldValue()) }
                                    DBTextField(
                                        "Enter Your Name",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Text,
                                            imeAction = ImeAction.Next
                                        ),
                                        nameTextState
                                    )
                                    DBTextField(
                                        "Enter Your Email",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Email,
                                            imeAction = ImeAction.Next
                                        ),
                                        emailTextState
                                    )
                                    DBTextField(
                                        "Enter Your Password",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Password,
                                            imeAction = ImeAction.Next
                                        ),
                                        confirmTextState
                                    )
                                    DBTextField(
                                        "Renter Your Password",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Password,
                                            imeAction = ImeAction.Next
                                        ),
                                        passwordTextState
                                    )


                                }
                                Column(
                                    Modifier
                                        .align(CenterHorizontally)
                                        .padding(bottom = 20.dp)) {
                                    DBButton(
                                        ToastText = "Account Created",
                                        btnText = "Register",
                                        context = context,
                                        intent = null
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

@Composable
fun InventoryScreen(inventoryItems: List<InventoryItem> = mockItems,){
    val context = LocalContext.current
    Scaffold(topBar = { MyTopAppBar() },
        floatingActionButton = {
        FloatingActionButton(
            onClick = {

                val intent =
                    Intent(context, EditInventoryActivity::class.java)
                startActivity(context, intent, null)
                },
                backgroundColor = colorResource(R.color.DByellow),
                contentColor = Color.White
                ){
                Icon(Icons.Filled.Edit,"")
            }
    }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))){
            LazyColumn(Modifier.align(Center)) {
                items(inventoryItems) { item ->
                    Card (
                        Modifier
                            .padding(20.dp, 10.dp)
                            .size(300.dp), shape = RoundedCornerShape
                        (5)){

                        item.items.forEach {

                            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                                item() {

                                        Column(Modifier.fillMaxSize(),
                                            horizontalAlignment =
                                            CenterHorizontally) {
                                            Text(
                                                text = "\n" + item.title + "\n",
                                                fontSize = 20.sp, color = Color.DarkGray, textAlign = TextAlign.Center
                                            )
                                        }
                                        item.items.forEach() { ingredient ->
                                            Column() {
                                                if(ingredient.name != "" ){
                                                    Text(ingredient.name + ", " + ingredient.count, color
                                                    = Color.DarkGray )
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
}

@Composable
fun EditInventoryScreen(inventoryItems: List<InventoryItem> = mockItems){

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(topBar = { MyTopAppBar() },
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    openDialog.value = !openDialog.value

                },
                backgroundColor = colorResource(R.color.DByellow),
                contentColor = Color.White
            ){
                Icon(Icons.Filled.Add,"")
            }
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))){
            LazyColumn(Modifier.align(Center)) {
                items(inventoryItems) { item ->
                    Card (
                        Modifier
                            .padding(20.dp, 10.dp)
                            .size(300.dp),
                        shape = RoundedCornerShape
                            (5)){
                        item.items.forEach {

                            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                                item() {

                                    Column(Modifier.fillMaxSize(), ){
                                        Row(Modifier.fillMaxSize(), horizontalArrangement =
                                        Arrangement.Center, verticalAlignment = CenterVertically){

                                            Text(
                                                text = "\n" + item.title + "\n",
                                                fontSize = 20.sp, color = Color.DarkGray, textAlign = TextAlign.Center
                                            )

                                            IconButton(
                                                onClick = { /*TODO*/ },
                                            ) {

                                                Icon(Icons.Filled.AddCircle, "", tint = Color
                                                    .DarkGray, )
                                            }

                                        }



                                    }

                                    item.items.forEach() { ingredient ->
                                        Row(Modifier.fillMaxSize(), verticalAlignment =
                                        CenterVertically){
                                            if(ingredient.name != "" ){
                                                Text(ingredient.name + ", " + ingredient.count, color
                                                = Color.DarkGray )
                                                Row(Modifier.fillMaxSize(),
                                                    horizontalArrangement = Arrangement.End){
                                                    IconButton(onClick = { /*TODO*/ }, Modifier.size
                                                        (25.dp)
                                                    ) {
                                                        Icon(Icons.Filled.Add,"", tint = Color.DarkGray )
                                                    }
                                                    IconButton(onClick = { /*TODO*/ }, Modifier.size
                                                        (25.dp)
                                                    ) {
                                                        Icon(Icons.Filled.Remove,"",tint = Color.DarkGray)
                                                    }
                                                    IconButton(onClick = { /*TODO*/ }, Modifier.size
                                                        (25.dp)
                                                    ) {
                                                        Icon(Icons.Filled.Delete,"", tint = Color.DarkGray)
                                                    }
                                                }
                                            }


                                        }
                                    }


                                }


                            }



                        }

//

                    }
                }
            }
            if (openDialog.value){

                Card(
                    Modifier
                        .align(Center)
                        .fillMaxSize()
                        .alpha(0.7f),backgroundColor = Color.White,
                    elevation =
                    0.dp){}
                    Card(
                        Modifier
                            .align(Center)
                            .padding(20.dp, 10.dp),

                        backgroundColor = Color.White,
                        shape = RoundedCornerShape
                            (5),
                    elevation = 4.dp){
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
//                            DBButton(ToastText = "Added!", btnText = "Add", context = context,
//                                intent = Intent(context, EditInventoryActivity::class.java))
                            Button(onClick = {
                                val intent = Intent(context, EditInventoryActivity::class.java)
                                startActivity(context, intent, null)

                               // val ingList = listOf<Ingredient>()
                                val ing = Ingredient("", "");
                                val ingList = listOf<Ingredient>(ing)
                                val newInventoryItem: InventoryItem = InventoryItem(NewCategoryTextState.value.text,ingList)


                                mockItems.add(newInventoryItem)


                                             },
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(50.dp)

                                ) {
                                Text(text = "Add", modifier = Modifier, color = Color.White,
                                    fontSize
                                = 15.sp)
                            }

                        }


                    }




            }
        }

    }


}
@Composable
fun RecipeScreen(recipe: Recipe = spaghetti){
    val context = LocalContext.current
    Scaffold(topBar = { MyTopAppBar() }) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))){

            LazyColumn() {
                item{
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp),horizontalAlignment = CenterHorizontally) {
                        Text(text = recipe.name, fontSize = 25.sp, color = Color.DarkGray)
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)) {
                        Text(text = "Ingredients",fontSize = 20.sp, color = Color.DarkGray, textAlign = TextAlign.Center)
                        recipe.ingredients.forEach(){
                            Text(text = it , color = Color.DarkGray)
                        }
//                        Text(text = recipe.ingredients.toString())
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)) {
                        Text(text = "Instructions",fontSize = 20.sp, color = Color.DarkGray,
                            textAlign = TextAlign.Center)
                        recipe.instructions.forEach(){
                            Text(text = it , color = Color.DarkGray)
                        }
                    }
                }

            }
        }
    }

}

