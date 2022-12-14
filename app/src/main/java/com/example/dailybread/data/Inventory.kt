package com.example.dailybread.data

import androidx.compose.runtime.toMutableStateList
import com.example.dailybread.datastore.InventoryStore
import com.example.dailybread.user.UserManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

data class Inventory(val ingList: List<Category>)
data class Category(var title: String, val items: MutableList<Ingredient>)


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

