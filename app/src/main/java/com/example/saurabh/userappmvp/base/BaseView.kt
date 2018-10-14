package com.example.saurabh.userappmvp.base

import android.content.Intent
import com.example.saurabh.userappmvp.ui.BasePresenter

interface BaseView<T : BasePresenter> {
    fun setPresenter(presenter: T)
    fun openFragment(id : Int,fragment: BaseFragment<T>){

    }
    fun openActivityIntent(intent : Intent){

    }
}