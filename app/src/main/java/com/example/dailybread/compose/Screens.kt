package com.example.dailybread.compose

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.R
import com.example.dailybread.data.*




@Composable
fun AccountScreen(openDrawer: () -> Unit){
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    Scaffold(topBar = {MyTopAppBar(title = "Account",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() })}) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFFCF7EC))){
            LazyColumn {
               item {
                Card(Modifier.fillMaxSize()){
                    Column(Modifier.fillMaxSize()) {
                        Text("username")
                        Text(text = "Change Password", Modifier.clickable { openDialog.value = !openDialog.value })
                    }

                }
               }
            }
            if (openDialog.value) {

                Card(
                    Modifier
                        .align(Center)
                        .fillMaxSize()
                        .alpha(0.7f), backgroundColor = Color.White,
                    elevation =
                    0.dp
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
                        Button(
                            onClick = {


                            },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.textButtonColors(Color(0xFFFDAF01)),
                            modifier = Modifier
                                .width(120.dp)
                                .height(50.dp)

                        ) {
                            Text(
                                text = "Confirm", modifier = Modifier, color = Color.White,
                                fontSize
                                = 15.sp
                            )
                        }

                    }


                }


            }
        }
    }
}


