package com.example.dailybread

import android.app.Application
import com.example.dailybread.user.UserManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DailyBreadApp: Application() {

    override fun onCreate() {
        super.onCreate()
        MainScope().launch {
            UserManager.getUserFromStore(this@DailyBreadApp)
        }
    }
}