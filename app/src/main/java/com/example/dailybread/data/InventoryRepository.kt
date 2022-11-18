package com.example.dailybread.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

object InventoryRepository {

    var isUnSaved = false


    private val inventory = SnapshotStateList<Category>()
    //    .apply { addAll(mockItems) }
    fun getInventory(): MutableList<Category> = inventory
    fun setInventory(categories: List<Category>) {
        inventory.clear()
        val toState = categories.map {
            val itemsAsState = it.items.toMutableStateList()
            it.copy(items = itemsAsState)
        }
        inventory.addAll(toState)
    }
    fun deleteIngredient(item: Category, ingredient: Ingredient): Boolean {
        isUnSaved = true
        return inventory.find { it == item }?.items?.remove(ingredient) ?: false
    }
    fun getIngredientsString(): String {
        return inventory.flatMap { it.items }.joinToString()
    }

    fun addIngredient(item: Category, ingredient: Ingredient): Boolean {
        isUnSaved = true
        return item.items.add(ingredient)
    }
    fun editIngredient(item: Category, ingredient: Ingredient, newName: String, newCount: String){
        isUnSaved = true
        val index = item.items.indexOf(ingredient)
        val updatedIng = Ingredient(newName, newCount)
        item.items[index] = updatedIng
    }

    fun addCategory(item: Category): Boolean {
        isUnSaved = true
        return inventory.add(item)
    }

    fun removeCategory(item: Category): Boolean {
        isUnSaved = true
        return inventory.remove(item)
    }
}