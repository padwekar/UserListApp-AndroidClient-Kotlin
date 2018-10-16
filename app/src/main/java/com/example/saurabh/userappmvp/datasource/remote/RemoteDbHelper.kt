package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.UserRepositoryContract
import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Inject

interface UserOperation {

    @GET("userlist")
    fun fetchUserList() : Flowable<MutableList<User>>

    @POST("update")
    infix fun updateUser(@Body user : User) : Flowable<User>

    @POST("delete")
    infix fun deleteUser(@Body user: User) : Flowable<User>

    @POST("add")
    infix fun addUser(@Body user : User) : Flowable<User>

}

class RemoteDbHelper {

    @Inject
    lateinit var userOperation: UserOperation

}