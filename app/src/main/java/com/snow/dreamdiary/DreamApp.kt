package com.snow.dreamdiary

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DreamApp : Application(){

    override fun onCreate() {
        super.onCreate()
        DreamApp.context = this
    }

    companion object{
        private var context: Context? = null

        fun getAppContext(): Context{
            return context ?: throw IllegalStateException("Context is null!")
        }
    }

}