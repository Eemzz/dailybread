package com.example.dailybread.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.retrofit.Retro
import com.example.dailybread.user.UserManager

object InventoryRepository {

    var emptyCategory = mutableListOf<Category>()
    var isUnSaved = false
    var errorMessage = "";
    var added = mutableStateOf(false)
    var deleted = mutableStateOf(false)
    var toAdd = mutableListOf(mutableListOf<String>())
    var catToAdd = mutableListOf<Category>()
    var toRemove = mutableListOf<String>()
    var catToRemove = mutableListOf<String>()
    var toEdit = mutableListOf(mutableListOf<String>())

    private val inventory = SnapshotStateList<Category>()
    //    .apply { addAll(mockItems) }
    fun getInventory(): MutableList<Category> = inventory

    //suspend fun setInventory(categories: List<Category>) {
    suspend fun setInventory(email: String) {
        val emptyCats = getEmptyCategories()
        inventory.clear()
        //inventory.addAll(emptyCats)
        isUnSaved = false

        if (toEdit.size == 1 && toEdit.get(0).isEmpty())
        {
            toEdit.removeAll(toEdit)
        }
        if (toAdd.size == 1 && toAdd.get(0).isEmpty())
        {
            toAdd.removeAll(toAdd)
        }

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
        if (!catToRemove.isEmpty())
        {
            for (k in 0 until catToRemove.size)
            {
                removeCategory(catToRemove.get(k))
            }
            catToRemove.removeAll(catToRemove)
        }
        if (!catToAdd.isEmpty())
        {
            for (l in 0 until catToAdd.size)
            {
                addCategory(catToAdd.get(l))
            }
            catToAdd.removeAll(catToAdd)
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
                    if (!ingredients.getJSONObject(j).getString("name").isEmpty())
                    {
                        println("items: " + ingredients)
                        itemList.add(
                            Ingredient(
                                ingredients.getJSONObject(j).getString("name"),
                                ingredients.getJSONObject(j).getString("count")
                            )
                        )
                    }

                }
                categoryList.add(Category(list.getJSONObject(i).getString("category"), itemList))
            }

        }

        /*for (j in 0 until emptyCategory.size)
        {
            if (categoryList.contains(emptyCategory.get(j)))
            {
                emptyCategory.remove(emptyCategory.get(j))
            }
        }*/

        val toState = categoryList.map {
            val itemsAsState = it.items.toMutableStateList()
            it.copy(items = itemsAsState)
        }

        inventory.addAll(toState)
        //inventory.addAll(emptyCategory)
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
        val response = Retro.instance.checkInventory(ingredient.name, UserManager.useremail)
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

    suspend fun categoryToAdd(category: Category): Boolean {
        isUnSaved = true

        category.title = category.title.replaceFirstChar { it.titlecase() }
        val response = Retro.instance.checkCategory(category.title, UserManager.useremail)
        println("adding category to backend: " + response.message)
        val bool = response.message.toBoolean()
        println("category exists? " + bool.toString())

        if (!bool) {

            inventory.add(category)
            catToAdd.add(category)

            added.value = true
        }
        else {
            added.value = false
        }

        //return inventory.add(category)
        return added.value
    }

    suspend fun addCategory(item: Category) {
        Retro.instance.addCategory(item.title, UserManager.useremail)

    }

    fun categoryToRemove(item: Category): Boolean {
        isUnSaved = true

        catToRemove.add(item.title)

        return inventory.remove(item)
    }

    suspend fun removeCategory(category: String){
        //isUnSaved = true
        Retro.instance.deleteCategory(category, UserManager.useremail)

    }

    fun getEmptyCategories(): List<Category>{
        for (i in 0 until inventory.size)
        {
            if (inventory.get(i).items.isEmpty())
            {
                emptyCategory.add(inventory.get(i))
            }
        }

        return emptyCategory
    }
}