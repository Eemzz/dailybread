package com.example.dailybread.data

class Inventory(ingList: List<Category>)

val mockSpiceInventory = mutableListOf(
    Ingredient("Salt", "1 LB"),
    Ingredient("Pepper", "3 Oz")
)
val mockVegiInventory = mutableListOf(
    Ingredient("Tomatoes", "3"),
    Ingredient("Cucumbers", "3"),
    Ingredient("Carrots", "3"),
    Ingredient("Bell Pepper", "6"),
    Ingredient("Lettuce", "2"),
    Ingredient("Onion", "3"),
    Ingredient("Potatoes", "4"),
    Ingredient("Garlic", "1"),
    Ingredient("Mushrooms", "6"),

)
val mockProteinInventory = mutableListOf(
    Ingredient("Chicken Breast", "3 LB"),
    Ingredient("Tofu", "1 LB"),
)
val mockInventorySpiceItem =  Category("Spice", mockSpiceInventory)
val mockInventoryVegiItem =  Category("Vegetables", mockVegiInventory)
val mockInventoryProteinItem =  Category("Protein", mockProteinInventory)
val mockItems = mutableListOf(mockInventorySpiceItem, mockInventoryVegiItem,
    mockInventoryProteinItem)



data class Category(val title: String, val items: MutableList<Ingredient>)

