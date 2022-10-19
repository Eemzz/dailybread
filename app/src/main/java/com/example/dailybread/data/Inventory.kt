package com.example.dailybread.data

class Inventory(ingList: List<InventoryItem>) {

}
val mockSpiceInventory = listOf(
    Ingredient("Salt", "1 LB"),
    Ingredient("Pepper", "3 Oz")
)
val mockVegiInventory = listOf(
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
val mockProteinInventory = listOf(
    Ingredient("Chicken Breast", "3 LB"),
    Ingredient("Tofu", "1 LB"),
)
val mockInventorySpiceItem =  InventoryItem("Spice", mockSpiceInventory)
val mockInventoryVegiItem =  InventoryItem("Vegetables", mockVegiInventory)
val mockInventoryProteinItem =  InventoryItem("Protein", mockProteinInventory)
val mockItems = mutableListOf(mockInventorySpiceItem, mockInventoryVegiItem,
    mockInventoryProteinItem)



data class InventoryItem(val title: String, val items: List<Ingredient>){

}
