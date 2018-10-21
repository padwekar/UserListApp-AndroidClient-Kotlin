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

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    override fun onEditClick() {
        user?.let {
            view.openEditScreen(it)
        }
    }

    private fun fetchUserData(onStart : () -> Unit = { view.updateProgressVisibility(View.VISIBLE);view.updateErrorEmptyView()},
                              onSuccess : (User) -> Unit,
                              onError : (it : Throwable) -> Unit = {view.updateErrorEmptyView("Error","Something Went Wrong!")} ,
                              onComplete : () -> Unit = {view.updateProgressVisibility()}){

        compositeDisposable.add((repository.remote getUser userId ).doOnSubscribe {
            onStart()
        }.doFinally {
            onComplete()
        }.subscribe({
            onSuccess(it)
        },{
            onError(it)
        }
        ))


    }

}