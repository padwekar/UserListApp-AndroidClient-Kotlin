package com.example.saurabh.userappmvp.userlist.presentation

import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.disposables.CompositeDisposable

class UserListPresenter(val view : UserContract.View?,
                        val repository: UserRepository) : BasePresenter, UserContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        view?.showProgress()
        fetchUserList()
    }

    private fun fetchUserList() {
        compositeDisposable.add(repository.fetchUserList().doOnComplete {
                view?.showProgress(false)
         }.subscribe(
         {
                view?.showUsers(it)
                view?.updateErrorEmptyView()
         },{
                view?.updateErrorEmptyView("Error","Something Went Wrong!")
         }))
    }

    override fun onAddButtonClicked() {
        view?.showUserDetailScreen()
    }

    override fun onUserClicked(user : User) {
        view?.showUserDetailScreen(user.id)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}