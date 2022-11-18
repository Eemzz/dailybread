package com.example.dailybread.user

import android.util.MutableBoolean
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.dailybread.retrofit.DefaultResponse
import android.util.Log
import com.example.dailybread.data.*
import com.example.dailybread.retrofit.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = false
    var errorMessage = "";
    var registered = mutableStateOf(false)
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }

    fun setMessage(message: String) {
        errorMessage = message;
    }

    //sends user info via http POST request to backend
    fun createUser(name: String, email: String, password: String): Boolean {

        //var registered = mutableStateOf(false)

        //implement retrofit post request to server to register user
        Retro.instance.registerUser(name, email, password)
            .enqueue(object: Callback<DefaultResponse> {
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    //TODO("Not yet implemented"
                    val fromBackend = response.body()!!.message
                    println("message: " + fromBackend)
                    if (fromBackend == "registered")
                    {
                        setMessage("")
                        registered.value = true
                    }
                    else {
                        //println("frombackend != registered")
                        //errorMessage = response.body()!!.message
                        setMessage(fromBackend)
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    //TODO("Not yet implemented")
                    errorMessage = t.message.toString();
                    println("error message from backend:" + errorMessage);
                }
            })
        println("registered? " + registered.toString())
        return registered.value
    }

    fun loginUser(email: String, password: String) {
        Retro.instance.loginUser(email, password)
            .enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    //TODO("Not yet implemented")
                    println("response: " + response.body())
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    //TODO("Not yet implemented")
                    println(t.message)
                }

            })

        return null
    }



}