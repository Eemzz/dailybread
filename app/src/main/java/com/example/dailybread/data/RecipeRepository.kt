package com.example.dailybread.data

object RecipeRepository {
    var id = 0
    lateinit var recipes: Map<Int, Recipe>
    fun getRecipe(id: Int): Recipe = recipes.getValue(id)
}