package com.example.saurabh.userappmvp.datasource

import com.example.saurabh.userappmvp.datasource.local.LocalDataSource
import com.example.saurabh.userappmvp.datasource.local.LocalDbHelper
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.datasource.remote.RemoteDataSource
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.inject.Named


class UserRepository @Inject constructor(@Named("local") var local : UserRepositoryContract,
                                         @Named("remote") var remote : UserRepositoryContract)
    : UserRepositoryContract {

    override fun getUser(id: Int): Flowable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        var IS_CACHE_DIRTY = true
    }

    override fun fetchUserList() : Flowable<MutableList<User>> {
        if(!IS_CACHE_DIRTY){
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

    private fun getUsersAndSave() :  Flowable<MutableList<User>> {
        return remote.fetchUserList()
                .flatMap { user -> Flowable.fromIterable(user)
                        .doOnNext { task -> local.addUser(task) }
                        .toList()
                        .toFlowable()
                        .doOnComplete {
                            IS_CACHE_DIRTY = false
                        }
                }
    }

    fun <T> mergeAndExecute(task1 : Publisher<T>,task2: Publisher<T>) : Flowable<T>{
        return Flowable.merge(task1,task2)
                .doOnError {
                    IS_CACHE_DIRTY = true
                }
                .firstOrError()
                .toFlowable()
    }
}