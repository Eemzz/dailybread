package com.example.dailybread.user

import android.util.Log
import com.example.dailybread.data.*
import com.example.dailybread.retrofit.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserManager {
    var username = ""
    var userEmail = ""

    //TODO remove for session management
    var isUserLoggedIn = true
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }

    //sends user info via http POST request to backend
    fun createUser(name: String, email: String, password: String): Void? {
        //val user = User(name, email, password)
        //println(user)

        //implement retrofit post request to server
        //register user
        Retro.instance.registerUser(name, email, password)
            .enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    //TODO("Not yet implemented")
                    username = response.body()?.name.toString()
                    userEmail =  response.body()?.email.toString()
                    isUserLoggedIn = true

                    println(response.body());
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    //TODO("Not yet implemented")
                    println(t.message);
                }

            })

        return null
    }





}