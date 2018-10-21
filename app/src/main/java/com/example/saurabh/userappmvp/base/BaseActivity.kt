package com.example.saurabh.userappmvp.base

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onBackPressed() = when(supportFragmentManager.backStackEntryCount) {
            in 0..1 -> finish()
            else -> super.onBackPressed()
    }

}