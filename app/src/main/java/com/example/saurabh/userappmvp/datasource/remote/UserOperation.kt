package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface UserOperation {

    @GET("userlist")
    fun fetchUserList() : Single<MutableList<User>>

    @GET("getUser")
    infix fun getUser(@Query("id") id : Int) : Single<User>

    @POST("updateUser")
    infix fun updateUser(@Body user : User) : Single<User>

    @POST("delete")
    infix fun deleteUser(@Body user: User) : Single<User>

    @POST("add")
    infix fun addUser(@Body user : User) : Single<User>

    @Multipart
    @POST("convertAabToApk")
    fun convertBundleToApk(@Part bundleFile: MultipartBody.Part?): Single<ResponseBody>


}