package com.example.dailybread.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("message") @Expose
    val message: String
)