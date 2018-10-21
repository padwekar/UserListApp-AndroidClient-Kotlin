package com.example.saurabh.userappmvp.updateuser

import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.userdetail.UserDetailContract

class EditUserPresenter (var user : User?,var view : EditUserContract.View?,var repository: UserRepository) : BasePresenter,EditUserContract.Presenter {

    override fun onStart() {
        user?.let {
            view?.bind(it)
        }
    }

    override fun onDeleteClick() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSaveClick() {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}