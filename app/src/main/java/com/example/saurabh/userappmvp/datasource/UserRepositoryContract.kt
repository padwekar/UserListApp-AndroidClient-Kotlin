package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single

interface UserRepositoryContract{

    fun fetchUserList() : Single<MutableList<User>>
    fun saveList(list : MutableList<User>) : Single<User>
    infix fun updateUser(user : User) : Single<User>
    infix fun deleteUser(user: User) : Single<User>
    infix fun addUser(user : User) : Single<User>
    infix fun getUser(id : Int) : Single<User>

}