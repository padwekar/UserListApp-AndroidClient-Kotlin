package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Part
import javax.inject.Inject

class RemoteDataSource @Inject constructor(var remoteDbHelper: RemoteDbHelper): RemoteDataSourceContract {

    override fun saveList(list: MutableList<User>): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        DaggerUserComponent.create().inject(this)
    }

    override fun fetchUserList() : Single<MutableList<User>> = remoteDbHelper.getUsers()

    override fun getUser(id: Int): Single<User> = remoteDbHelper.getUser(id)

    override fun convertBundleToApk(@Part bundleFile: MultipartBody.Part?): Single<ResponseBody> {
        return remoteDbHelper.convertBundleToApk(bundleFile)

    }


    override fun updateUser(user: User) : Single<User> = remoteDbHelper.updateUser(user) //created functions use File | Settings | File Templates.


    override fun deleteUser(user: User) : Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addUser(user: User) : Single<User>{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}