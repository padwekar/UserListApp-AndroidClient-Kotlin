package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.local.LocalDbHelper
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import io.reactivex.Single
import okhttp3.MultipartBody
import org.reactivestreams.Publisher
import retrofit2.http.Part
import javax.inject.Inject
import javax.inject.Named


class UserRepository @Inject constructor(@Named("local") var local : UserRepositoryContract,
                                         @Named("remote") var remote : UserRepositoryContract)
    : UserRepositoryContract {

    override fun saveList(list: MutableList<User>): Single<User> = local.saveList(list)

    override fun getUser(id: Int): Single<User> =  remote.getUser(id)

    override fun convertBundleToApk(@Part bundleFile: MultipartBody.Part?)
        = remote.convertBundleToApk(bundleFile)


    companion object {
        var IS_CACHE_DIRTY = true
    }

    override fun fetchUserList() : Single<MutableList<User>> {
        if(!IS_CACHE_DIRTY && false){
            return local.fetchUserList()
        }
        return getUsersAndSave()
    }

    override fun updateUser(user: User) =
        mergeAndExecute(local.updateUser(user),remote.updateUser(user))

    override fun addUser(user: User) =
            mergeAndExecute(local.addUser(user),remote.addUser(user))

    override fun deleteUser(user: User) =
            mergeAndExecute(local.deleteUser(user),remote.deleteUser(user))

    private fun getUsersAndSave() :  Single<MutableList<User>> {
        return remote.fetchUserList()
                .doAfterSuccess{
                    //saveList(list = it)
                }.doAfterSuccess {
                    IS_CACHE_DIRTY = false
                }.doOnError {
                    IS_CACHE_DIRTY = true
                }
    }

    fun <T> mergeAndExecute(task1 : Single<T>,task2: Single<T>) : Single<T>{
        return Single.merge(task1,task2)
                .doOnError {
                    IS_CACHE_DIRTY = true
                }
                .firstOrError()

    }
}