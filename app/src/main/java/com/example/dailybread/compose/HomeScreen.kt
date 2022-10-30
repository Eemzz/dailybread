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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailybread.R
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }
    MyModalDrawer(drawerState, navController) {
        HomeScreen(openDrawer = openDrawer, navController = navController)
    }
}
@Composable
fun HomeScreen(navController: NavController,
               openDrawer: () -> Unit) {
    Scaffold(topBar = { MyTopAppBar(title = "Home",
        buttonIcon = Icons.Filled.Menu,
        onButtonClicked = { openDrawer() }) }) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.homebg3),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(180f),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn {
                item {
                    Column(
                        Modifier.fillMaxSize(), horizontalAlignment = Alignment
                            .CenterHorizontally
                    ) {
                        Card(
                            backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                            RoundedCornerShape(10), modifier =
                            Modifier
                                .padding(20.dp)
                                .fillMaxSize()
                        ) {


                            Column(
                                Modifier.padding(top = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Recipe Finder",
                                    fontSize = 25.sp,
                                    color = Color.DarkGray,
                                )

                                Column {
                                    val ingListTextState =
                                        remember { mutableStateOf(TextFieldValue()) }
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
                                Column(Modifier.padding(top = 10.dp, bottom = 20.dp)) {
                                    DBButton(
                                        btnText =
                                        "Search"
                                    ) {
                                        navController.navigate("recipe")
                                    }
                                }
                            }


                        }
                        Card(
                            backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                            RoundedCornerShape(10), modifier =
                            Modifier
                                .padding(20.dp)
                                .fillMaxSize()
                        ) {
                            Column(
                                Modifier.padding(20.dp), horizontalAlignment =
                                Alignment
                                    .CenterHorizontally
                            ) {
                                Text(
                                    text = "Recipe Generator",
                                    fontSize = 25.sp,
                                    color = Color.DarkGray
                                )
                                Column(Modifier.padding(10.dp)) {
                                    DBButton(
                                        btnText = "Generate"
                                    ) {
                                        //TODO make api call
                                        navController.navigate("recipe")
                                    }
                                }
                                Column(
                                    Modifier.padding(vertical = 10.dp)

                                ) {


                                    Text(
                                        text = "Generates a recipe from your inventory",
                                        fontSize = 14
                                            .sp, color =
                                        Color
                                            .DarkGray
                                    )

                                }
                            }

                        }
                        Card(backgroundColor = Color.White.copy(alpha = 0.75f), shape =
                        RoundedCornerShape(20), modifier =
                        Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                            .clickable {
                                navController.navigate("edit")
                            }
                        ) {
                            Column(
                                Modifier.padding(vertical = 20.dp), horizontalAlignment =
                                Alignment
                                    .CenterHorizontally
                            ) {
                                Text("View Inventory", color = Color.DarkGray, fontSize = 25.sp)
                            }

                        }

                    }
                }

            }


        }
    }
}