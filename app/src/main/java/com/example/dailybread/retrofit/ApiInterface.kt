package com.example.dailybread.retrofit

import com.example.dailybread.data.Ingredient
import com.example.dailybread.data.Recipe
import com.example.dailybread.user.User
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

//Retrofit interface to handle GET and POST requests
interface ApiInterface {

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

    //addIngredient saves to db
    @FormUrlEncoded
    @POST ("auth/addingredient")
    suspend fun addIngredient(
        @Field("category") category: String,
        @Field("item") item: String,
        @Field("quantity") quantity: String,
        @Field("email") email: String,
    ): InventoryResponse

    @FormUrlEncoded
    @POST ("auth/deleteingredient")
    suspend fun deleteIngredient(
        @Field("item") item: String,
        @Field("email") email: String,
    ): InventoryResponse

    //checkInventory is called in edit inventory page, before user saves to db
    @GET("auth/getingredient")
    suspend fun checkInventory(@Query("item") item: String): InventoryResponse

    @GET("auth/getinventory")
    suspend fun inventory(@Query("email") email: String): String

    @GET("/recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("/recipes")
    suspend fun getRecipesFromIngredients(@Query("q") ingredients: String): List<Recipe>

}