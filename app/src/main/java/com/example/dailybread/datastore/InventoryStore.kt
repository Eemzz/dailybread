package com.example.dailybread.datastore

import android.content.Context
import android.util.Log
import com.example.dailybread.data.Inventory
import com.example.dailybread.user.UserStore
import com.example.dailybread.retrofit.Retro
import com.example.dailybread.user.UserManager
import com.google.gson.Gson
import kotlinx.coroutines.delay
import org.json.JSONObject

object InventoryStore {
    private const val INVFile ="invfile"
    var list = ""
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

    suspend fun inventory(email: String): String {
        list = Retro.instance.inventory(email)
        delay(2000)
        println("list: " + list)
        return list;
    }

}