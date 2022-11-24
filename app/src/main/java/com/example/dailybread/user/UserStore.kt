package com.example.dailybread.user

import android.content.Context
import android.util.Log
import com.google.gson.Gson

object UserStore {
    private const val USERFile ="USERFile"
    fun writeUser(context: Context, user: User) {
        val body = Gson().toJson(user)
        val fileOut = context.openFileOutput(USERFile, Context.MODE_PRIVATE)
        fileOut.write(body.toByteArray())
        fileOut.close()
    }

    suspend fun readUser(context: Context): User? {
        return try {
            val input = context.openFileInput(USERFile).reader()
            Gson().fromJson(input, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun delete(context: Context) {
        val wasDeleted = context.deleteFile(USERFile)
        Log.d("TAG", "User deleted: $wasDeleted")
    }
}