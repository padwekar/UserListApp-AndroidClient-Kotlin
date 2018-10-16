package com.example.saurabh.userappmvp.app

import android.app.Application
import io.paperdb.Paper

class TodoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}