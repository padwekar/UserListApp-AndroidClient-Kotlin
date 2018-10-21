package com.example.saurabh.userappmvp.userlist.presentation

import android.view.View.VISIBLE
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.disposables.CompositeDisposable

class UserListPresenter(val view : UserContract.View?,
                        val repository: UserRepository) : BasePresenter, UserContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun onStart() {
        fetchUserList(onSuccess = {
            view?.showUsers(it)
            view?.updateErrorEmptyView()
        })
    }

    override fun onRetry() {
        onStart()
    }

    private fun fetchUserList(onStart : () -> Unit = {  view?.updateProgressVisibility(VISIBLE) },
                              onSuccess : (list : MutableList<User>) -> Unit,
                              onError : (it : Throwable) -> Unit = {view?.updateErrorEmptyView("Error","Something Went Wrong!")} ,
                              onComplete : () -> Unit = {  view?.updateProgressVisibility() }) {

        compositeDisposable.add(repository.fetchUserList().doOnSubscribe {
                onStart()
        }.doOnComplete {
                onComplete()
         }.subscribe({
                onSuccess(it)
         },{
                onError(it)
                onComplete()
        }))
    }

    override fun onAddButtonClicked() {
        view?.showUserDetailScreen()
    }

    override fun onUserClicked(user : User) {
        view?.showUserDetailScreen(user.id ?: 0)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}