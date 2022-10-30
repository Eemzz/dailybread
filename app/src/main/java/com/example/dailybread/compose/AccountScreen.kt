package com.example.dailybread.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailybread.user.ChangePassword

import kotlinx.coroutines.launch

@Composable
fun AccountScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        AccountScreen(openDrawer = openDrawer)
    }
}
@Composable
fun AccountScreen(openDrawer: () -> Unit){
    val context = LocalContext.current
    val openDialog = remember {
        mutableStateOf(false)
    }
    Scaffold(topBar = {MyTopAppBar(title = "Account",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() })}) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))
        ) {
            LazyColumn(Modifier.align(Center)) {
                item {
                    Card(
                        Modifier
                            .padding(20.dp, 10.dp)
                            .width(300.dp)
                            .fillMaxHeight(),
                        shape = RoundedCornerShape
                            (15)) {
                        Column(Modifier.padding(30.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Column(Modifier.padding(16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "User Information", color = Color.DarkGray, fontSize = 25.sp)
                            }


                                Text(text = "Username", color = Color.DarkGray, fontSize = 20.sp) //TODO make this display username



                                Text(text ="Email", color = Color.DarkGray, fontSize = 20.sp) //TODO make this display email

                            Column(Modifier.padding( 16.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                                Button(onClick = { openDialog.value = !openDialog.value },shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                                    modifier = Modifier
                                        .height(60.dp)
                                        .padding(5.dp)) {
                                    Text(text = "Change Password",modifier = Modifier, color = Color.White,
                                        fontSize
                                        = 15.sp)
                                }
                            }


                           // }

                        }

                    }
                }
            }
            if (openDialog.value) {

                Card(
                    Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .alpha(0.7f), backgroundColor = Color.White,
                    elevation =
                    0.dp
                ) {}
                Card(
                    Modifier
                        .align(Alignment.Center)
                        .padding(20.dp, 10.dp),

                    backgroundColor = Color.White,
                    shape = RoundedCornerShape
                        (5),
                    elevation = 4.dp
                ) {
                    val NewCategoryTextState = remember { mutableStateOf(TextFieldValue()) }
                    Column(Modifier.padding(16.dp), horizontalAlignment = CenterHorizontally) {
                        Text(text = "Change Password", color = Color.DarkGray, fontSize = 20.sp)
                        DBTextField(

                            "Current Password",
                            KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
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
//
                        Row(Modifier.padding(top = 16.dp)){
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


                    }


                }


            }
        }
    }
}