package com.example.dailybread.datastore

import android.content.Context
import com.example.dailybread.data.Inventory
import com.google.gson.Gson

object InventoryStore {
    private const val INVDIR ="invdir"
    private const val INVFile ="invfile"
    fun writeInventory(context: Context, inventory: Inventory) {
        val body = Gson().toJson(inventory)
        val fileOut = context.openFileOutput(INVFile, Context.MODE_PRIVATE)
        fileOut.write(body.toByteArray())
        fileOut.close()
    }

    suspend fun readInventory(context: Context): Inventory {
        val input = context.openFileInput(INVFile).reader()
        return Gson().fromJson(input, Inventory::class.java)
    }
}