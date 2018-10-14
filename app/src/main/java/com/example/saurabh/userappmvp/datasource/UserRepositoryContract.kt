package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.model.User
import io.reactivex.Single

interface UserRepositoryContract{

    fun fetchUser() : Single<List<User>>
    fun updateUser(user : User) : Single<Boolean>
    fun deleteUser(user: User) : Single<Boolean>
    fun addUser(user : User) : Single<Boolean>

}