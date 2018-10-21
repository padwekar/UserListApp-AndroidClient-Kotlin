package com.example.saurabh.userappmvp.userdetail

import android.opengl.Visibility
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.base.BaseView
import com.example.saurabh.userappmvp.datasource.model.User

interface UserDetailContract {

    interface View : BaseView<Presenter>{
        fun bindData(user : User)
        fun openEditScreen(user : User)
    }

    interface Presenter : BasePresenter {
        fun onEditClick()
    }
}