package com.example.mindand

import android.app.Application
import android.util.Log
//import com.example.mindand.data.AppContainer
//import com.example.mindand.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MasterAndApplication: Application() {
//    lateinit var container: AppContainer
//    override fun onCreate() {
//        super.onCreate()
//        Log.d("MasterAndApplication", "Application onCreate called")
//        container = AppDataContainer(this)
//        Log.d("MasterAndApplication", "COntainer initialized")
//    }
}