@file:Suppress("NOTHING_TO_INLINE")

package com.example.saurabh.userappmvp.datasource.local

import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import io.reactivex.Single
import javax.inject.Inject

class LocalDataSource @Inject constructor(var localDbHelper: LocalDbHelper)  : LocalDataSourceContract {
    override fun saveList(list: MutableList<User>): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(id: Int): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        DaggerUserComponent.create().inject(this)
    }


    override fun fetchUserList() = localDbHelper.fetchUserList()

    override fun updateUser(user: User) = localDbHelper updateUser user

    override fun deleteUser(user: User) = localDbHelper deleteUser user

    override fun addUser(user: User) = localDbHelper addUser user

}

