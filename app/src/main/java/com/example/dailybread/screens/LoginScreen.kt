package com.example.dailybread.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dailybread.R
import com.example.dailybread.user.UserManager
import com.example.dailybread.compose.DBButton
import com.example.dailybread.compose.DBLogo
import com.example.dailybread.compose.DBTextField

@Composable
fun LoginScreen(navController: NavController) {
    val emailTextState =
        remember { mutableStateOf(TextFieldValue()) }
    val passwordTextState =
        remember { mutableStateOf(TextFieldValue()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.appbg1),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn {
            item {
                Column(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .padding(top = 20.dp), horizontalAlignment = Alignment
                        .CenterHorizontally
                ) {
                    DBLogo(name = "DailyBread")
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp)) {
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
                                            imeAction = ImeAction.Done
                                        ),
                                        passwordTextState,
                                        visualTransformation = PasswordVisualTransformation()
                                    )
                                    Text(
                                        buildAnnotatedString {
                                            append("Don't have an account? Sign up ")
                                            withStyle(style = SpanStyle(color = Color.Blue,
                                                textDecoration = TextDecoration.Underline)){
                                                append("here")
                                            }
                                        },
                                    modifier = Modifier
                                        .padding(top = 16.dp)
                                        .clickable {  navController.navigate("signup")})

                                }
                                Column(
                                    Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(bottom = 20.dp)
                                ) {
                                    DBButton(
                                        btnText = "Log in"
                                    ) {
                                        //TODO verify user
                                        UserManager.loginUser(emailTextState.value.text, passwordTextState.value.text)
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
