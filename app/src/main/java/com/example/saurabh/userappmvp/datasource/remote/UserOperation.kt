package com.example.saurabh.userappmvp.datasource.remote

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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


}