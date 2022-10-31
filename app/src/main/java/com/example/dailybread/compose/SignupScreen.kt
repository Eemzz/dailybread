package com.example.dailybread.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dailybread.R

@Composable
fun SignUpScreen(navController: NavController) {
    val nameTextState =
        remember { mutableStateOf(TextFieldValue()) }
    val emailTextState =
        remember { mutableStateOf(TextFieldValue()) }
    val confirmTextState =
        remember { mutableStateOf(TextFieldValue()) }
    val passwordTextState =
        remember { mutableStateOf(TextFieldValue()) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.appbg1),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxSize()
                .rotate(180f),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn {
            item {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp), horizontalAlignment = Alignment
                        .CenterHorizontally
                ) {
                    DBLogo(name = "DailyBread")
                    Column(
                        Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Card(
                            backgroundColor = Color.White.copy(alpha = 0.75f),
                            shape = RoundedCornerShape(5),
                            modifier = Modifier
                                .padding(20.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Column(Modifier.padding(20.dp)) {

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
                                        "Enter Your Password",
                                        KeyboardOptions(
                                            autoCorrect = false,
                                            keyboardType = KeyboardType.Password,
                                            imeAction = ImeAction.Done
                                        ),
                                        passwordTextState
                                    )


                                }
                                Column(
                                    Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(bottom = 20.dp)
                                ) {
                                    DBButton(
                                        btnText = "Register"
                                    ) {
                                        //TODO add user to data base

                                        navController.navigate("home")
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
