package com.example.saurabh.userappmvp.updateuser

import android.view.View
import com.example.saurabh.userappmvp.base.BaseView
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.model.User

interface EditUserContract {

    interface View : BaseView<Presenter>{
        fun close()
        fun bind(user: User)
    }

    interface Presenter : BasePresenter {
        fun onDeleteClick()
        fun onSaveClick()
    }
}