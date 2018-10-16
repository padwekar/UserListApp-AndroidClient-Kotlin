package com.example.saurabh.userappmvp.dependency

import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.remote.RemoteDbHelper

interface UserComponent {
    //Data Source
    fun inject(remoteDbHelper: RemoteDbHelper)
    fun inject(userRepository: UserRepository)
}
