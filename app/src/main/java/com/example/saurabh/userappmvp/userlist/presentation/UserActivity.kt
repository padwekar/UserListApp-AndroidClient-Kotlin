package com.example.saurabh.userappmvp.userlist.presentation

import android.os.Bundle
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseActivity
import com.example.saurabh.userappmvp.extenstion.replace
import com.example.saurabh.userappmvp.updateuser.UserAddUpdateFragment

class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        replace(R.id.container,UserAddUpdateFragment.newInstance())
    }

}
