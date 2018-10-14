@file:Suppress("NOTHING_TO_INLINE")

package com.example.saurabh.userappmvp.extenstion
import android.support.v7.app.AppCompatActivity
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.ui.BasePresenter


inline fun AppCompatActivity.replace(id : Int,fragment: BaseFragment<BasePresenter>) {
    supportFragmentManager.
    beginTransaction().
    addToBackStack(null).
    replace(id,fragment).
    commit()
}