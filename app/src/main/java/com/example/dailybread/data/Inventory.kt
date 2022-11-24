package com.example.dailybread.data

import androidx.compose.runtime.toMutableStateList
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.user.UserManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

data class Inventory(val ingList: List<Category>)
data class Category(val title: String, val items: MutableList<Ingredient>)

suspend fun myInventory(email: String): Inventory {
    var list = InventoryStore.inventory(email).getJSONArray("inventory")
    println("list: " + list.length())
    println("list type: " + list.javaClass.name)
    val categoryList = mutableListOf<Category>()

    /*val typeToken = object : TypeToken<List<Category>>() {}.type

    val categories = Gson().fromJson<List<Category>>(list, typeToken)

    println(categories)*/
    for (i in 0 until list.length())
    {
        val itemList = mutableListOf<Ingredient>()
        val items = list.getJSONObject(i).getJSONObject("items")
        val ingredients = items.getJSONArray("items")
        for (j in 0 until ingredients.length())
        {
            //println("items: " + ingredients.getJSONObject(0).get("name"))
            itemList.add(Ingredient(ingredients.getJSONObject(j).getString("name"), ingredients.getJSONObject(j).getString("count")))
        }
        categoryList.add(Category(list.getJSONObject(i).getString("category"), itemList))
    }

    println("categories: " + categoryList)

    return Inventory(categoryList)
}


private val mockSpiceInventory = listOf(
    Ingredient("Salt", "1 LB"),
    Ingredient("Pepper", "3 Oz")
).toMutableStateList()
private val mockVegiInventory = listOf(
    Ingredient("Tomatoes", "3"),
    Ingredient("Cucumbers", "3"),
    Ingredient("Carrots", "3"),
    Ingredient("Bell Pepper", "6"),
    Ingredient("Lettuce", "2"),
    Ingredient("Onion", "3"),
    Ingredient("Potatoes", "4"),
    Ingredient("Garlic", "1"),
    Ingredient("Mushrooms", "6"),

).toMutableStateList()
private val mockProteinInventory = listOf(
    Ingredient("Chicken Breast", "3 LB"),
    Ingredient("Tofu", "1 LB"),
).toMutableStateList()
val mockInventorySpiceItem =  Category("Spice", mockSpiceInventory)
val mockInventoryVegiItem =  Category("Vegetables", mockVegiInventory)
val mockInventoryProteinItem =  Category("Protein", mockProteinInventory)
val mockItems = mutableListOf(mockInventorySpiceItem, mockInventoryVegiItem,
    mockInventoryProteinItem)

