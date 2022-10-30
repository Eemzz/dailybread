package com.example.dailybread.user

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = true
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }
}