package com.example.dailybread.screens

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
        navController.navigate("login") {
            popUpTo("router") {
                inclusive = true
            }
        }
    }

}