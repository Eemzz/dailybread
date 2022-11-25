package com.example.dailybread.user

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.retrofit.Retro

object UserManager {
    var isUserLoggedIn = false
    var errorMessage = "";
    var registered = mutableStateOf(false)
    var passwordChanged = mutableStateOf(false)
    var username = "";
    var useremail = "";

    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }

    fun setMessage(message: String) {
        errorMessage = message;
    }

    //sends user info via http POST request to backend
    suspend fun createUser(name: String, email: String, password: String) : Boolean {

        val response = Retro.instance.registerUser(name, email, password)
        val fromBackend = response.message
        println("message: " + fromBackend)

        if (fromBackend == "registered") {
            setMessage("")
            isUserLoggedIn = true
            registered.value = true
            username = name;
            useremail = email;
        } else {

            setMessage(fromBackend)
        }
        println("registered? " + registered.value.toString())
        return registered.value
    }

    suspend fun loginUser(email: String, password: String, context: Context): Boolean {

        val response = Retro.instance.loginUser(email, password)
        val fromBackend = response.message
        println("message: $fromBackend")

        if (fromBackend == "logged in") {
            setMessage("")
            useremail = response.email
            username = response.user
            isUserLoggedIn = true
            UserStore.writeUser(context, User(response.user, email, password))
        } else {
            setMessage(fromBackend)
        }

        return isUserLoggedIn
    }

    suspend fun changePassword(email: String, password: String, newPassword: String): Boolean {
        val response = Retro.instance.changePassword(email, password, newPassword)
        if (response.message == "password changed") {
            passwordChanged.value = true
        }
        else
        {
            passwordChanged.value = false
        }

        return passwordChanged.value
    }

    fun getUserEmail(): String {
        return username
    }

    fun getUserName(): String {
        return useremail
    }
    fun logout(context: Context) {
        username = ""
        useremail = ""
        isUserLoggedIn = false
        UserStore.delete(context)
        InventoryStore.delete(context)
    }

    suspend fun getUserFromStore(context: Context) {
        val user = UserStore.readUser(context)
        if (user != null) {
            username = user.name
            useremail = user.email
            isUserLoggedIn = true
        } else {
            isUserLoggedIn = false
        }
    }

}