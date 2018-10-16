package com.example.saurabh.userappmvp.userlist.presentation

import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.base.BaseView
import com.example.saurabh.userappmvp.datasource.model.User

interface UserContract {

    interface View : BaseView<Presenter> {
        fun showProgress(visible : Boolean = true)
        fun showUsers(users : MutableList<User>)
        fun updateErrorEmptyView(title : String = "", message : String = "")
        fun showUserDetailScreen(userId : Int = -1)
        fun showAddUserScreen()
    }

    interface Presenter : BasePresenter {
        fun onAddButtonClicked()
        fun onUserClicked(user : User)
    }

}