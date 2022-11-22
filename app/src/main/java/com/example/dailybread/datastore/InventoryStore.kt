package com.example.dailybread.datastore

import android.content.Context
import com.example.dailybread.data.Inventory
import com.example.dailybread.retrofit.Retro
import com.example.dailybread.user.UserManager
import com.google.gson.Gson
import kotlinx.coroutines.delay
import org.json.JSONObject

object InventoryStore {
    private const val INVDIR ="invdir"
    private const val INVFile ="invfile"
    var list = ""
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

    suspend fun inventory(email: String): String {
        list = Retro.instance.inventory(email)
        delay(2000)
        println("list: " + list)
        return list;
    }

}