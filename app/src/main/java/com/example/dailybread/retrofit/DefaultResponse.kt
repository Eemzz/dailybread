package com.example.dailybread.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.util.Objects

data class DefaultResponse(
    @SerializedName("message") @Expose val message: String,
    @SerializedName("user") @Expose val user: String,
    @SerializedName("email")  @Expose val email: String
)
