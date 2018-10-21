package com.example.saurabh.userappmvp.base

import android.content.Intent
import android.support.v4.app.Fragment

interface BaseView<T> {

    fun updateProgressVisibility(visibility: Int = android.view.View.GONE){

    }

    fun updateErrorEmptyView(title : String = "", message : String = ""){

    }

    fun openFragment(id : Int,fragment: Fragment){

    }
    fun openActivityIntent(intent : Intent){

    }
}