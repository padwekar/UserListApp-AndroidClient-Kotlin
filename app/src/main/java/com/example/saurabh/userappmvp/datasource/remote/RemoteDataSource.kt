package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import io.reactivex.Flowable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(var remoteDbHelper: RemoteDbHelper): RemoteDataSourceContract {


    init {
        DaggerUserComponent.create().inject(this)
    }

    override fun fetchUserList() : Flowable<MutableList<User>> = remoteDbHelper.getUsers()

    override fun getUser(id: Int): Flowable<User> = remoteDbHelper.getUser(id)

    override fun updateUser(user: User) : Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(user: User) : Flowable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: User) : Flowable<Boolean>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}