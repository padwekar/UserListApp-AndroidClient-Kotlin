package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import io.reactivex.Flowable
import javax.inject.Inject


class RemoteDbHelper @Inject constructor(var userOperation : UserOperation) {

    fun getUsers() : Flowable<MutableList<User>> = userOperation.fetchUserList()

}