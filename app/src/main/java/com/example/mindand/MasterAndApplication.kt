package com.example.mindand

import android.app.Application
import com.example.mindand.data.AppContainer
import com.example.mindand.data.AppDataContainer

class MasterAndApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}