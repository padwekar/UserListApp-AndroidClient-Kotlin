package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Flowable

class RemoteDataSource : RemoteDataSourceContract {
    override fun fetchUserList() : Flowable<MutableList<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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