package com.example.dailybread.data

import com.google.gson.Gson
import org.junit.Assert.*

import org.junit.Test

class RecipeTest {

    @Test
    fun getName() {
        val gson = Gson()
        val response = RecipeResponse(listOf(spaghetti, biryani, fettuccine))
        val gsonString = gson.toJson(response)
        println(gsonString)
    }
}