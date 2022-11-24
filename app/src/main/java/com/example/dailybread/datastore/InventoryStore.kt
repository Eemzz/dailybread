package com.example.dailybread.datastore

import android.content.Context
import android.util.Log
import com.example.dailybread.data.Inventory
import com.example.dailybread.user.UserStore
import com.google.gson.Gson

object InventoryStore {
    private const val INVFile ="invfile"
    fun writeInventory(context: Context, inventory: Inventory) {
        val body = Gson().toJson(inventory)
        val fileOut = context.openFileOutput(INVFile, Context.MODE_PRIVATE)
        fileOut.write(body.toByteArray())
        fileOut.close()
    }

    suspend fun readInventory(context: Context): Inventory {
        return try {
            val input = context.openFileInput(INVFile).reader()
            Gson().fromJson(input, Inventory::class.java)
        } catch (e: Exception) {
            Inventory(listOf())
        }
    }

    fun delete(context: Context) {
        val wasDeleted = context.deleteFile(INVFile)
        Log.d("TAG", " Inventory deleted: $wasDeleted")
    }
}