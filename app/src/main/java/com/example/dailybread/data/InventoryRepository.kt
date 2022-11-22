package com.example.dailybread.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.retrofit.DefaultResponse
import com.example.dailybread.retrofit.Retro
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

    private val inventory = SnapshotStateList<Category>()
    //    .apply { addAll(mockItems) }
    fun getInventory(): MutableList<Category> = inventory

    suspend fun setInventory(categories: List<Category>) {
    //suspend fun setInventory(email: String) {
        inventory.clear()
        val list = InventoryStore.inventory(UserManager.useremail)
        println("inventory items: " + list.toString())
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

    suspend fun waitAdd() {
        delay(2000)
    }

    fun addIngredient(item: Category, ingredient: Ingredient): Boolean = runBlocking {
        //var added = false
        isUnSaved = true
        ingredient.name = ingredient.name.replaceFirstChar { it.titlecase() }
        //println("ingredient to add: " + item.title + ", " + ingredient.name + ", " + ingredient.count + ", " + UserManager.useremail)
        Retro.instance.addIngredient(item.title, ingredient.name, ingredient.count, UserManager.useremail)
            .enqueue(object: Callback<DefaultResponse> {
                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    //TODO("Not yet implemented")
                    val fromBackend = response.body()!!.message
                    println("message: " + fromBackend)
                    if (fromBackend == "added") {
                        added.value = item.items.add(ingredient)
                    }
                    else {
                        errorMessage = fromBackend
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    //TODO("Not yet implemented")
                    val message = t.message.toString()
                    println("error message from backend: " + message)
                }

            })
        waitAdd()
        added.value
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