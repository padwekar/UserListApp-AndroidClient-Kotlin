package com.example.saurabh.userappmvp.datasource.local

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single

class LocalDataSource : LocalDataSourceContract {
    override fun fetchUser() : Single<List<User>> = Single.create{

    }

    override fun updateUser(user: User) : Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(user: User) : Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: User) : Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

