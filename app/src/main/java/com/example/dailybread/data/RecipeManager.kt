package com.example.dailybread.data

import android.util.Log
import com.example.dailybread.retrofit.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RecipeManager {

    fun getRecipes(): RecipeResponse? {

        Retro.instance.getRecipes()
            .enqueue(object: Callback<List<Recipe>> {
                override fun onResponse(
                    call: Call<List<Recipe>>,
                    response: Response<List<Recipe>>
                ) {
                    val names = response.body()?.map {
                        it.name
                    }
                    RecipeRepository.recipes = response.body().orEmpty()
                    Log.d("TAG", "onResponse: $names")
//                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
//                    TODO("Not yet implemented")
                }

            })

        return null
    }
}