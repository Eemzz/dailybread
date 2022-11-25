package com.example.dailybread.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.retrofit.Retro
import com.example.dailybread.user.UserManager

object InventoryRepository {

    var isUnSaved = false
    var errorMessage = "";
    var added = mutableStateOf(false)
    var deleted = mutableStateOf(false)
    var toAdd = mutableListOf(mutableListOf<String>())
    var toRemove = mutableListOf<String>()
    var toEdit = mutableListOf(mutableListOf<String>())

    private val inventory = SnapshotStateList<Category>()
    //    .apply { addAll(mockItems) }
    fun getInventory(): MutableList<Category> = inventory

    //suspend fun setInventory(categories: List<Category>) {
    suspend fun setInventory(email: String) {
        inventory.clear()
        isUnSaved = false
        //println("is unsaved value in setInventory: " + isUnSaved.toString())
        //println("to add: " + toAdd)
        if (toEdit.size == 1 && toEdit.get(0).isEmpty())
        {
            toEdit.removeAll(toEdit)
        }
        if (toAdd.size == 1 && toAdd.get(0).isEmpty())
        {
            toAdd.removeAll(toAdd)
        }
        /*println("to edit: " + toEdit)
        println("to add: " + toAdd)
        //println("to edit: " + toEdit)
        println("to remove: " + toRemove)*/
        if (!toEdit.isEmpty())
        {
            for (i in 0 until toEdit.size)
            {
                editIngredient(toEdit.get(i).get(0), toEdit.get(i).get(1), toEdit.get(i).get(2), email)
            }
            toEdit.removeAll(toEdit)
        }
        if (!toAdd.isEmpty())
        {
            for (i in 0 until toAdd.size)
            {
                addIngredient(toAdd.get(i).get(0), toAdd.get(i).get(1), toAdd.get(i).get(2), email)
            }
            toAdd.removeAll(toAdd)
        }
        if (!toRemove.isEmpty())
        {
            for (j in 0 until toRemove.size)
            {
                deleteIngredient(toRemove.get(j))
            }
            toRemove.removeAll(toRemove)
        }
        /*println("to edit after: " + toEdit)
        println("to add after: " + toAdd)
        //println("to edit: " + toEdit)
        println("to remove after: " + toRemove)*/

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

    suspend fun editIngredient(ingredient: String, newName: String, newCount: String, email: String){
        //isUnSaved = true

        Retro.instance.editIngredient(ingredient, newName, newCount, email)

    }

    fun editThisIngredient(item: Category, ingredient: Ingredient, newName: String, newCount: String){
        isUnSaved = true
        val newItem = newName.replaceFirstChar { it.titlecase() }
        var editThis = mutableListOf<String>()
        editThis.add(ingredient.name)
        editThis.add(newItem)
        editThis.add(newCount)

        toEdit.add(editThis)
        println("to edit: " + toEdit)

        val index = item.items.indexOf(ingredient)
        val updatedIng = Ingredient(newItem, newCount)
        item.items[index] = updatedIng
    }

    fun addCategory(item: Category): Boolean {
        isUnSaved = true
        item.title = item.title.replaceFirstChar { it.titlecase() }

        return inventory.add(item)
    }

    fun removeCategory(item: Category): Boolean {
        isUnSaved = true
        return inventory.remove(item)
    }
}