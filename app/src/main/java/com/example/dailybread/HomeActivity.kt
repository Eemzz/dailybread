package com.example.dailybread

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.compose.*
import com.example.dailybread.ui.theme.DailyBreadTheme

class HomeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBreadTheme {
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
                        Column(Modifier.fillMaxSize(),horizontalAlignment = Alignment
                            .CenterHorizontally) {
                            Card( shape = RoundedCornerShape(10),modifier = Modifier
                                // .align(Alignment.TopCenter)
                                .size(300.dp, 250.dp)
                                .padding(vertical = 20.dp)
                                .alpha(0.75f)
                                .background(Color.Transparent)){



                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(text = "Recipe Finder", fontSize = 25.sp, color = Color.DarkGray,modifier = Modifier.padding(70.dp,10.dp))

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
                                    Column(Modifier.padding(10.dp)) {
                                        DBButton(ToastText = "Looking for recipes", btnText =
                                        "Search", context = this@HomeActivity )
                                    }
                                }


                            }
                            Card( shape = RoundedCornerShape(10),modifier = Modifier
                                // .align(Alignment.BottomCenter)
                                .size(300.dp, 200.dp)
                                .padding(vertical = 20.dp)
                                .alpha(0.75f)
                                .background(Color.Transparent)){
                                Column(Modifier.padding(vertical = 30.dp),horizontalAlignment =
                                Alignment
                                    .CenterHorizontally
                                ) {
                                    DBButton(ToastText = "Generating Recipe", btnText = "Generate",
                                        context = this@HomeActivity)
                                    Column(Modifier.padding(vertical = 10.dp)

                                    ) {


                                        Text(text = "Generates A Recipe From Your Inventory",
                                            fontSize = 15
                                            .sp, color =
                                        Color
                                            .DarkGray)

                                    }
                                }

                            }

                            Card(shape = RoundedCornerShape(20),modifier = Modifier

                                .size(300.dp, 100.dp)
                                .padding(vertical = 20.dp)
                                .alpha(0.75f)
                                .clickable {
                                    val intent =
                                        Intent(this@HomeActivity, InventoryActivity::class.java)
                                    startActivity(intent)
                                }// navigate to
                                // inventory
                                // page
                                .background(Color.Transparent)) {
                                Column(Modifier.padding(vertical = 20.dp),horizontalAlignment =
                                Alignment
                                    .CenterHorizontally) {
                                    Text("View Inventory" )
                                }

                            }

                        }






                    }
                }




            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DailyBreadTheme {
//
        }
    }
}