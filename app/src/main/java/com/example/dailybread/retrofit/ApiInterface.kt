package com.example.dailybread.retrofit

import com.example.dailybread.data.Recipe
import retrofit2.http.*

//Retrofit interface to handle GET and POST requests
interface ApiInterface {

    @FormUrlEncoded
    @POST ("auth/registeruser")
    suspend fun registerUser(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ): UserResponse

    @FormUrlEncoded
    @POST("auth/loginuser")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserResponse

    @FormUrlEncoded
    @POST ("auth/changepassword")
    suspend fun changePassword(
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("newPass") newPass: String
    ): DefaultResponse

    //addIngredient saves to db
    @FormUrlEncoded
    @POST ("auth/addingredient")
    suspend fun addIngredient(
        @Field("category") category: String,
        @Field("item") item: String,
        @Field("quantity") quantity: String,
        @Field("email") email: String,
    ): DefaultResponse

    @FormUrlEncoded
    @POST ("auth/deleteingredient")
    suspend fun deleteIngredient(
        @Field("item") item: String,
        @Field("email") email: String,
    ): DefaultResponse

    @FormUrlEncoded
    @POST ("auth/editingredient")
    suspend fun editIngredient(
        @Field("item") item: String,
        @Field("newName") newName: String,
        @Field("newCount") newCount: String,
        @Field("email") email: String
    ): DefaultResponse

    //checkInventory is called in edit inventory page, before user saves to db
    @GET("auth/getingredient")
    suspend fun checkInventory(@Query("item") item: String): DefaultResponse

    @GET("auth/getinventory")
    suspend fun inventory(@Query("email") email: String): String

    @GET("/recipes")
    suspend fun getRecipes(): List<Recipe>

    @GET("/recipes")
    suspend fun getRecipesFromIngredients(@Query("q") ingredients: String): List<Recipe>

}