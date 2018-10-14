package com.example.saurabh.userappmvp.userlist.presentation

import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.ui.BasePresenter
import io.reactivex.Single
import java.util.*

class UserListPresenter(val view : UserContract.View,val repository: UserRepository) : BasePresenter , UserContract.Presenter {

    private fun fetchUserList() {
         repository.fetchUser().subscribe({

         },{

         })
    }

    private fun addUser(){
        repository.fetchUser().subscribe({

        },{

        })
    }

    private fun showDetails(){

    }

    override fun onAddButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onStart() {
        view.showProgress()
        fetchUserList()
    }



}