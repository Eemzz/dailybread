package com.example.dailybread.user

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.dailybread.retrofit.Retro
import okhttp3.internal.Internal.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = false
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }


    fun createUser(name: String, email: String, password: String): Boolean {
        val user = User(name, email, password)
        println(user)

        //implement retrofit post request to server
        //register user
        Retro.instance.registerUser(user.name, user.email, user.password)
            .enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    //TODO("Not yet implemented")
                    println(response.body());
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    //TODO("Not yet implemented")
                    println(t.message);
                    Log.d("UserManger", "onFailure"+t.message)
                }

            })

        return true
    }



}