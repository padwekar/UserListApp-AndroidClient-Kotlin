package com.example.saurabh.userappmvp.dependency

import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import com.example.saurabh.userappmvp.datasource.remote.RemoteDbHelper
import com.example.saurabh.userappmvp.file.FileActivity
import com.example.saurabh.userappmvp.updateuser.EditUserFragment
import com.example.saurabh.userappmvp.userdetail.UserDetailFragment
import com.example.saurabh.userappmvp.userlist.presentation.UserListFragment
import dagger.Component

@AppScope
@Component(modules = [RepositoryModule::class,NetworkModule::class])
interface UserComponent {
    fun inject(userListFragment: UserListFragment)
    fun inject(userDetailFragment : UserDetailFragment)
    fun inject(localDataSource: LocalDataSource)
    fun inject(remoteDataSource: RemoteDataSource)
    fun inject(remoteDataSource: UserRepository)
    fun inject(remoteDbHelper: RemoteDbHelper)
    fun inject(editUserFragment: EditUserFragment)
    fun inject(fileActivity: FileActivity)

}
