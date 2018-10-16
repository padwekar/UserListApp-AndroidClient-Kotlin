package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Flowable

interface UserRepositoryContract{

    fun fetchUserList() : Flowable<MutableList<User>>
    infix fun updateUser(user : User) : Flowable<Boolean>
    infix fun deleteUser(user: User) : Flowable<Boolean>
    infix fun addUser(user : User) : Flowable<Boolean>

}