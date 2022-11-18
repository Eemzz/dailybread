package com.example.dailybread.data

import com.example.dailybread.retrofit.Retro

object RecipeManager {

    suspend fun getRecipes(): List<Recipe> {
        val recipes = Retro.instance.getRecipes()
        RecipeRepository.recipes = recipes.associateBy { it.id }
        return recipes
    }
    suspend fun getRecipesFromIng(ingList: String): List<Recipe> {
        val recipes = Retro.instance.getRecipesFromIngredients(ingList)
        RecipeRepository.recipes = recipes.associateBy { it.id }
        return recipes
    }
}