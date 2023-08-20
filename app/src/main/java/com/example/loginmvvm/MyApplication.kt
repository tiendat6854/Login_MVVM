package com.example.loginmvvm

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getApplicationContext(): Context {
            return instance.applicationContext
        }
    }
}
