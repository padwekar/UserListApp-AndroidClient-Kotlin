package com.example.saurabh.userappmvp.userlist.presentation

import android.view.View
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.base.BaseView
import com.example.saurabh.userappmvp.datasource.model.User

interface UserContract {

    interface View : BaseView<Presenter> {
        fun showUsers()
        fun showUserDetailScreen(userId : Int = -1)
    }

    interface Presenter : BasePresenter {
        var userList : MutableList<User>
        var totalItemCount : Int
        fun userAtPosition(position : Int) = userList[position]
        fun onAddButtonClicked()
        fun onUserClicked(user : User)
        fun onRetry()
    }

}