package com.example.dailybread.retrofit

import com.example.dailybread.data.Recipe
import com.example.dailybread.user.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//Retrofit interface to handle GET and POST requests
interface ApiInterface {

    @GET ("posts")
    fun getData(): Call<User>

    @FormUrlEncoded
    @POST ("registeruser")
    fun registerUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ): Call<User>


    @GET("/recipes")
    fun getRecipes():Call<List<Recipe>>


}