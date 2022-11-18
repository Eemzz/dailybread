package com.example.dailybread.user

import android.util.MutableBoolean
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.dailybread.retrofit.DefaultResponse
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import com.example.dailybread.data.*
import com.example.dailybread.retrofit.Retro
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = false
    var errorMessage = "";
    var registered = mutableStateOf(false)
    var username = "";
    var useremail = "";

    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }

    fun setMessage(message: String) {
        errorMessage = message;
    }

    //sends user info via http POST request to backend
    fun createUser(name: String, email: String, password: String) : Boolean = runBlocking{

        //var registered = mutableStateOf(false)
        username = name;
        useremail = email;
        //implement retrofit post request to server to register user
        Retro.instance.registerUser(name, email, password)
            .enqueue(object: Callback<DefaultResponse> {
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    //TODO("Not yet implemented"
                    val fromBackend = response.body()!!.message
                    println("message: " + fromBackend)
                    //GlobalScope.launch(Dispatchers.Main){
                    //  withContext(Dispatchers.IO) {
                    if (fromBackend == "registered") {
                        setMessage("")
                        registered.value = true
                    } else {
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
        //result.await();*/
        /*GlobalScope.launch{
            val result = Retro.instance.register(name, email, password)
            println("result: " + result)
            /*if (result != null)
            {
                registered.value = true
            }*/
            println("registered? " + registered.toString())

        }*/
        /*GlobalScope.launch {
            val response = makeRegReq(name, email, password)
            println("return from makeRegReq: " + response)
        }*/

        /*val res = response.await()
        println("res: " + res)

        //println("error message: " + errorMessage)
        if (res != null)
        {
            registered.value = true
        }*/

        wait()

        println("registered? " + registered.value.toString())
        //return registered.value
        registered.value
    }

    suspend fun wait() {
        delay(4000)
    }

    /*suspend fun makeRegReq(name: String, email: String, password: String) : Response<DefaultResponse> {
        val result = Retro.instance.register(name, email, password).execute()
        /*val result = Retro.instance.registerUser(name, email, password)
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
            })*/
        println("result: " + result)
        return result
    }*/

    fun loginUser(email: String, password: String): Boolean {

        Retro.instance.loginUser(email, password)
            .enqueue(object: Callback<DefaultResponse> {
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    //TODO("Not yet implemented")
                    val fromBackend = response.body()!!.message
                    println("response: " + response.body())
                    if (fromBackend == "logged in")
                    {
                        setMessage("")
                        isUserLoggedIn = true
                    }
                    else {
                        //println("frombackend != registered")
                        //errorMessage = response.body()!!.message
                        setMessage(fromBackend)
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    //TODO("Not yet implemented")
                    errorMessage = t.message.toString()
                    println(t.message)
                }

            })

        return isUserLoggedIn
    }

    fun getUserEmail(): String {
        return username
    }

    fun getUserName(): String {
        return useremail
    }


}