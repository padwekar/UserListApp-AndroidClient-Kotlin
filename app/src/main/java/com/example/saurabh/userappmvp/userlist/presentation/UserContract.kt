package com.example.saurabh.userappmvp.userlist.presentation

import com.example.saurabh.userappmvp.ui.BasePresenter
import com.example.saurabh.userappmvp.base.BaseView

interface UserContract {

    interface View : BaseView<Presenter> {
        fun showProgress(visible : Boolean = true)
        fun showUsers()
    }

    interface Presenter : BasePresenter {
        fun onAddButtonClicked()
        fun onUserClicked()
    }

}