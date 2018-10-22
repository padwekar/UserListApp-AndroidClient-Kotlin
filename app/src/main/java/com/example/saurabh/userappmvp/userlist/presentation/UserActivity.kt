package com.example.saurabh.userappmvp.userlist.presentation

import android.os.Bundle
import android.view.MenuItem
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseActivity
import com.example.saurabh.userappmvp.extenstion.replace
import com.example.saurabh.userappmvp.userdetail.UserDetailFragment

class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        replace(R.id.container,UserListFragment.newInstance())
     //   replace(R.id.container, UserDetailFragment.newinstance())


    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item!!.itemId) {
        android.R.id.home -> { onBackPressed(); true }
        else -> super.onOptionsItemSelected(item)
    }




}
