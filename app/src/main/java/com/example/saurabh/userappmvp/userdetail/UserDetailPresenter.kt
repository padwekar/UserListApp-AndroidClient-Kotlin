package com.example.saurabh.userappmvp.userdetail


import android.view.View
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.disposables.CompositeDisposable

class UserDetailPresenter(var userId : Int,var view : UserDetailContract.View,var repository: UserRepository) : BasePresenter , UserDetailContract.Presenter {

    var user : User ?= null

    val compositeDisposable = CompositeDisposable()


    override fun onStart() {
        fetchUserData(onSuccess = { it -> user = it; view.bindData(it)})
    }

    override fun onEdit() {
        user?.let {
            view.openEditScreen(it)
        }
    }

    private fun fetchUserData(onStart : () -> Unit = { view.updateProgressVisibility(View.VISIBLE);view.updateEmptyView()},
                              onSuccess : (User) -> Unit,
                              onError : (it : Throwable) -> Unit = {view.updateEmptyView("Error","Something Went Wrong!")} ,
                              onComplete : () -> Unit = {view.updateProgressVisibility()}){

        compositeDisposable.add((repository.remote getUser userId ).doOnSubscribe {
            onStart()
        }.doOnComplete {
            onComplete()
        }.subscribe({
            onSuccess(it)
        },{
            onComplete()
            onError(it)
        }
        ))


    }

}