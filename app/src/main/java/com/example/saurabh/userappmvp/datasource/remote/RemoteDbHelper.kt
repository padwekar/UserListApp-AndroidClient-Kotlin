package com.example.saurabh.userappmvp.datasource.remote

import io.reactivex.Single
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RemoteDbHelper @Inject constructor(var userOperation : UserOperation) {

    fun getUsers() = this smooth userOperation.fetchUserList()


    fun getUser(id : Int) = this smooth userOperation.getUser(id)


    private infix fun <T :Any> smooth(flow : Single<T>) = flow.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())



}