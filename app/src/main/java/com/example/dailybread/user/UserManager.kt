package com.example.dailybread.user

import androidx.compose.runtime.Composable

object UserManager {
    //TODO remove for session management
    var isUserLoggedIn = true
    fun isLoggedIn(): Boolean {
        return isUserLoggedIn
    }

}
@Composable
fun ChangePassword(oldPassword: String, newPassword: String ){
    //TODO
}