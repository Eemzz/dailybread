package com.example.dailybread

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailybread.compose.DBLogo
import com.example.dailybread.compose.DBTextField
import com.example.dailybread.ui.theme.DailyBreadTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyBreadTheme {

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

                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 20.dp, horizontal = 20.dp)
//                            .paddingFromBaseline(0.dp, 390.dp)
                            .alpha(0.75f)
                            .size(380.dp)
//                            .height(380.dp)
//                            .width(315.dp)
                            .background(Color.White)
                            .clip(RoundedCornerShape(5))


                    ) {

//                        Card(modifier = Modifier
//                            .align(Alignment.BottomCenter)
//                            .height(380.dp)
//                            .width(315.dp)
//                            .background(Color.White)){}

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
                        DBButton(ToastText = "Account Created", btnText = "Register",context = this@SignUpActivity)
                    }

                }
            }
        }
    }
}



@Composable
fun DBButton(ToastText: String, btnText: String, context: Context) {
    Box(Modifier
        .absolutePadding(80.dp, 10.dp, 20.dp, 40.dp)) {
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
    TopAppBar(
        title = { Text("DailyBread")},
        backgroundColor = Color(0xFFFDAF01),
        contentColor = Color.Yellow
    )
    //use this function to make the top bar on main page activity:
    //    Scaffold(topBar = { MyTopAppBar() }) {}

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DailyBreadTheme {
//        Greeting("Android")
    }


}