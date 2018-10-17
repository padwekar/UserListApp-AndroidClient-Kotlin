@file:Suppress("NOTHING_TO_INLINE")

package com.example.saurabh.userappmvp.datasource.local

import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import javax.inject.Inject

class LocalDataSource @Inject constructor(var localDbHelper: LocalDbHelper)  : LocalDataSourceContract {

    init {
        DaggerUserComponent.create().inject(this)
    }


    override fun fetchUserList() = localDbHelper.fetchUserList()

    override fun updateUser(user: User) = localDbHelper updateUser user

    override fun deleteUser(user: User) = localDbHelper deleteUser user

    override fun addUser(user: User) = localDbHelper addUser user

}

