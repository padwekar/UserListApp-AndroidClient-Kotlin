package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Part
import javax.inject.Inject

class RemoteDbHelper @Inject constructor(var userOperation : UserOperation) {

    fun getUsers() = this smooth userOperation.fetchUserList()


    fun getUser(id : Int) = this smooth userOperation.getUser(id)

    fun updateUser(user : User) = this smooth userOperation.updateUser(user)

    fun convertBundleToApk(bundleFile: MultipartBody.Part?) : Single<ResponseBody>
            = this smooth userOperation.convertBundleToApk(bundleFile)


    private infix fun <T :Any> smooth(flow : Single<T>) = flow.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())




}