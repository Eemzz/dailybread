package com.example.dailybread.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dailybread.user.UserManager

@Composable
fun RouterScreen(navController: NavController) {
    if (UserManager.isLoggedIn()) {
        navController.navigate("home") {
            popUpTo("router") {
                inclusive = true
            }
        }
    } else {
        navController.navigate("signup") {
            popUpTo("router") {
                inclusive = true
            }
        }
    }

}