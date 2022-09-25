package com.example.dailybread.data

class Inventory {
    var ingList = arrayListOf<String>()
    public fun addIng(newIng:String){
        ingList.add(newIng)
    }
}
val mockSpiceInventory = listOf(
    Ingredient("Salt", "1 LB"),
    Ingredient("Pepper", "3 Oz")
)
val mockVegiInventory = listOf(
    Ingredient("Tomatoes", "3"),
    Ingredient("Cucumbers", "3"),
    Ingredient("Carrots", "3"),
)
val mockProteinInventory = listOf(
    Ingredient("Chicken Breast", "3 LB"),
    Ingredient("Tofu", "1 LB"),
)
val mockInventorySpiceItem =  InventoryItem("Spice", mockSpiceInventory)
val mockInventoryVegiItem =  InventoryItem("Vegetables", mockVegiInventory)
val mockInventoryProteinItem =  InventoryItem("Protein", mockProteinInventory)
val mockItems = listOf(mockInventorySpiceItem, mockInventoryVegiItem, mockInventoryProteinItem)
data class InventoryItem(val title: String, val items: List<Ingredient>)
