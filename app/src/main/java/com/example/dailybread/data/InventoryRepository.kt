package com.example.dailybread.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.retrofit.DefaultResponse
import com.example.dailybread.retrofit.Retro
import com.example.dailybread.user.User
import com.example.dailybread.user.UserManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object InventoryRepository {

    var isUnSaved = false
    var errorMessage = "";
    var added = mutableStateOf(false)
    var deleted = mutableStateOf(false)
    var toAdd = mutableListOf(mutableListOf<String>())
    var toRemove = mutableListOf<String>()

    private val inventory = SnapshotStateList<Category>()
    //    .apply { addAll(mockItems) }
    fun getInventory(): MutableList<Category> = inventory

    //suspend fun setInventory(categories: List<Category>) {
    suspend fun setInventory(email: String) {
        inventory.clear()
        isUnSaved = false
        //println("is unsaved value in setInventory: " + isUnSaved.toString())
        //println("to add: " + toAdd)
        if (toAdd.size !== 1)
        {
            for (i in 1 until toAdd.size)
            {
                addIngredient(toAdd.get(i).get(0), toAdd.get(i).get(1), toAdd.get(i).get(2), email)
            }
        }
        if (!toRemove.isEmpty())
        {
            for (j in 0 until toRemove.size)
            {
                deleteIngredient(toRemove.get(j))
            }
        }

        var list = InventoryStore.inventory(email).getJSONArray("inventory")
        //println("list: " + list.length())
        //println("list type: " + list)
        val categoryList = mutableListOf<Category>()

        for (i in 0 until list.length()) {
            val itemList = mutableListOf<Ingredient>()
            val items = list.getJSONObject(i).getJSONObject("items")
            val ingredients = items.getJSONArray("items")
            if (ingredients.length() !== 0)
            {
                for (j in 0 until ingredients.length()) {
                    //println("items: " + ingredients.getJSONObject(0).get("name"))
                    itemList.add(
                        Ingredient(
                            ingredients.getJSONObject(j).getString("name"),
                            ingredients.getJSONObject(j).getString("count")
                        )
                    )
                }
                categoryList.add(Category(list.getJSONObject(i).getString("category"), itemList))
            }

        }

        //println("categories: " + categoryList)

        inventory.addAll(categoryList)
    }

    fun tempDeleteIngredient(item: Category, ingredient: Ingredient): Boolean {
        isUnSaved = true

        toRemove.add(ingredient.name)

        println("removing: " + inventory.find { it == item }?.items?.remove(ingredient))
        println("new inventory: " + inventory.find { it == item }?.items.toString())
        return inventory.find { it == item }?.items?.remove(ingredient) ?: false
    }

    suspend fun deleteIngredient(ingredient: String) {
        Retro.instance.deleteIngredient(ingredient, UserManager.useremail)

    }

    fun getIngredientsString(): String {
        return inventory.flatMap { it.items }.joinToString()
    }

    suspend fun checkIngredient(category: Category, ingredient: Ingredient): Boolean {
        isUnSaved = true
        ingredient.name = ingredient.name.replaceFirstChar { it.titlecase() }

        val response = Retro.instance.checkInventory(ingredient.name)
        val bool = response.message.toBoolean()
        println("item exists? " + bool.toString())

        var addThis = mutableListOf<String>()
        if (!bool) {
            addThis.add(category.title)
            addThis.add(ingredient.name)
            addThis.add(ingredient.count)

            toAdd.add(addThis)

            added.value = true
        }
        else {
            added.value = false
        }

        return category.items.add(ingredient)
    }

    suspend fun addIngredient(category: String, item: String, quantity: String, email: String): Boolean {

        val response = Retro.instance.addIngredient(category, item, quantity, email)
        val fromBackend = response.message
        //println("message: " + fromBackend)

        if (fromBackend == "added") {
            added.value = true
        } else {
            added.value = false
            errorMessage = fromBackend
        }
        return added.value
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