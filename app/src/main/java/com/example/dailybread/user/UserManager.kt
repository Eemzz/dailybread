package com.example.dailybread.user

import com.example.dailybread.retrofit.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = false
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