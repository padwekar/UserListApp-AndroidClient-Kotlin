@file:Suppress("NOTHING_TO_INLINE")

package com.example.saurabh.userappmvp.extenstion
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.base.BasePresenter
import kotlinx.android.synthetic.main.activity_user.view.*


inline fun AppCompatActivity.replace(id : Int,fragment: Fragment) {
    supportFragmentManager.
    beginTransaction().
    addToBackStack(null).
    replace(id,fragment).
    commit()
}

inline fun Fragment.replace(id : Int = R.id.container, fragment: Fragment){
    (activity as AppCompatActivity).replace(id,fragment)
}