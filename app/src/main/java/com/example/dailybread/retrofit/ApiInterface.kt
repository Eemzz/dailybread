package com.example.dailybread.retrofit

import com.example.dailybread.data.Ingredient
import com.example.dailybread.data.Recipe
import com.example.dailybread.user.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

//Retrofit interface to handle GET and POST requests
interface ApiInterface {

    @GET ("posts")
    fun getData(): Call<User>

    @FormUrlEncoded
    @POST ("auth/registeruser")
    suspend fun registerUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ): DefaultResponse

    @FormUrlEncoded
    @POST("auth/loginuser")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): DefaultResponse

    @FormUrlEncoded
    @POST ("auth/addingredient")
    fun addIngredient(
        @Field("category") category: String,
        @Field("item") item: String,
        @Field("quantity") quantity: String,
        @Field("email") email: String,
    ): Call<DefaultResponse>

    @GET("auth/getinventory")
    suspend fun inventory(@Query("email") email: String): String

    @GET("/recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("/recipes")
    suspend fun getRecipesFromIngredients(@Query("q") ingredients: String): List<Recipe>

}