package com.example.dailybread.compose

import android.content.Context
import android.view.ActionMode
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailybread.HomeActivity
import com.example.dailybread.InventoryActivity
import com.example.dailybread.R
import com.example.dailybread.data.InventoryItem
import com.example.dailybread.data.mockItems


@Composable
fun DBTextField(label: String, keyboardOptions: KeyboardOptions, textState: MutableState<TextFieldValue>) {
    Column(Modifier.padding(5.dp)) {
        OutlinedTextField(
            shape = RoundedCornerShape(20),
            value = textState.value,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            label = { Text(text = label) },
            onValueChange = { textState.value = it }
        )

    }
}

@Composable
fun DBLogo(name: String) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color(0xFFFDAF01)
        ),

        modifier = Modifier
            .width(300.dp)
            .height(140.dp)


    ) {
        Text(text = name, modifier = Modifier, color = Color.White, fontSize = 50.sp)

    }
}

@Composable
fun DBButton(ToastText: String, btnText: String, context: Context) {
    Box(Modifier
       // .absolutePadding(80.dp, 10.dp, 20.dp, 40.dp)
   ) {

        Button(onClick = { Toast.makeText(context, ToastText, Toast.LENGTH_SHORT).show() },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
            modifier = Modifier
                .width(120.dp)
                .height(50.dp)
        ) {
            Text(text = btnText, modifier = Modifier, color = Color.White, fontSize = 15.sp)
        }
   }

}

@Composable
fun MyTopAppBar(){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors( colorResource(R
            .color.DByellow)),
        navigationIcon =  {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "", tint = Color.White)

            }
        },
        title = {
            Text("DailyBread", textAlign = TextAlign.Center, modifier = Modifier, color = Color.White
            )},

       // backgroundColor = Color(0xFFFDAF01),
       // contentColor = Color.White

    )
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("signup") { SignUpScreen() }
    }
}
@Composable
fun HomeScreen(){
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

            Card( shape = RoundedCornerShape(10),modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .alpha(0.75f)
                .background(Color.Transparent)){



                Column(
                    Modifier
                        .align(Alignment.TopCenter)) {

                    Text(text = "Recipe Finder", fontSize = 25.sp, color = Color.DarkGray,modifier = Modifier.padding(70.dp,10.dp))
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
                    val context = LocalContext.current
                    DBButton(ToastText = "Looking for recipes", btnText = "Search", context = context )
                }

            }
            Card( shape = RoundedCornerShape(10),modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .alpha(0.75f)
                .clickable { }// navigate to inventory page
                .background(Color.Transparent)){

                Column(
                    Modifier
                        .align(Alignment.BottomCenter)) {

                    Text(text = "Your Inventory", fontSize = 25.sp, color = Color.DarkGray,modifier = Modifier.padding(70.dp,10.dp))



                }
            }



        }
    }
}

@Composable
fun SignUpScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.appbg1),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxSize()
                .rotate(180f),
            contentScale = ContentScale.FillBounds
        )
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .absolutePadding(0.dp, 20.dp, 0.dp, 0.dp)){
            DBLogo(name = "DailyBread")
        }

        Card(
            shape = RoundedCornerShape(5),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 20.dp, horizontal = 20.dp)
//                            .paddingFromBaseline(0.dp, 390.dp)
                .alpha(0.75f)
                .size(380.dp)
//                            .height(380.dp)
//                            .width(315.dp)
            // .background(Color.White)
            // .clip(RoundedCornerShape(5))


        ) {


        }
        Column(Modifier.align(Alignment.BottomCenter)) {
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
            val context = LocalContext.current
            DBButton(ToastText = "Account Created", btnText = "Register",context = context)

        }

    }
}

@Composable
fun InventoryScreen(inventoryItems: List<InventoryItem> = mockItems){
    Scaffold(topBar = { MyTopAppBar() },
        floatingActionButton = {
        FloatingActionButton(
            onClick = {

                //pop up
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
                                    Column() {
                                        Text(
                                            text = "\n" + item.title + "\n",
                                            textDecoration = TextDecoration.Underline,
                                            fontSize = 20.sp,
                                        )
                                    }
                                    item.items.forEach() { ingredient ->
                                        Column() {
                                            Text(ingredient.name + ", " + ingredient.count,)
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
fun MultiFloatingActionButton() {

}