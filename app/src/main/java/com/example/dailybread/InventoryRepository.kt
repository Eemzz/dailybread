package com.example.dailybread

import com.example.dailybread.data.Ingredient
import com.example.dailybread.data.Category
import com.example.dailybread.data.mockItems
import kotlinx.coroutines.flow.*

object InventoryRepository {

    private val mockItemsFlow: MutableSharedFlow<List<Category>> = MutableSharedFlow()
    val setInventory: FlowCollector<List<Category>> = mockItemsFlow
    fun getInventory(): Flow<List<Category>>  = mockItemsFlow

    fun deleteIngredient(item: Category, ingredient: Ingredient): Boolean {
        return mockItems.find { it == item }?.items?.remove(ingredient) ?: false
    }
//    fun lessOfIngredient(item: Category, ingredient: Ingredient) {
//
//        val newCount = ingredient.count
//        ingredient.setCount(newCount)
//
//    }
//    fun moreOfIngredient(item: Category, ingredient: Ingredient) {
//        val newCount = ingredient.count - 1
//        ingredient.setCount(newCount)
//
//    }
    fun addIngredient(item: Category, ingredient: Ingredient): Boolean {
        return item.items.add(ingredient)
    }
    fun editIngredient(item: Category, ingredient: Ingredient, newName: String, newCount: String){
        val index = item.items.indexOf(ingredient)
        val updatedIng = Ingredient(newName, newCount)
        item.items[index] = updatedIng

    }

    fun addCategory(item: Category): Boolean {
        return mockItems.add(item)
    }
    fun removeCategory(item: Category): Boolean {
        return mockItems.remove(item)
    }

}