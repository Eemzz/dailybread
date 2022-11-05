package com.example.dailybread.data

object RecipeRepository {
    var id = 0
    lateinit var recipes: List<Recipe>
    fun getRecipe(id: Int): Recipe = recipes.first { it.id == id }
}