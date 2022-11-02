package com.example.dailybread.user

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController

import androidx.compose.runtime.Composable

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = true
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }



    fun createUser(name: String, email: String, password: String): Boolean {
        val user = User(name, email, password)
        println(user)

        //implement retrofit post request to server
        //register user

        return true
    }



}