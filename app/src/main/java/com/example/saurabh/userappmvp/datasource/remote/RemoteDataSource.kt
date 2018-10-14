package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single

class RemoteDataSource : RemoteDataSourceContract {
    override fun fetchUser() : Single<List<User>> = Single.create{

    }

    override fun updateUser(user: User) : Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(user: User) : Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: User) : Single<Boolean>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}