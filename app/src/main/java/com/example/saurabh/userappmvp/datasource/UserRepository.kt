package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepository(val local : LocalDataSource,val remote : RemoteDataSource) : UserRepositoryContract {

    override fun fetchUser() : Single<List<User>> {
        return remote.fetchUser().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
       }

    override fun updateUser(user: User) : Single<Boolean> {
        local.updateUser(user)
        return Single.create{
            remote.updateUser(user)
        }
    }

    override fun deleteUser(user: User) : Single<Boolean> {
        local.deleteUser(user)
        return Single.create{
            remote.deleteUser(user)
        }
    }

    override fun addUser(user: User) : Single<Boolean> {
        local.addUser(user)
        return Single.create{
            remote.addUser(user)
        }
    }
}