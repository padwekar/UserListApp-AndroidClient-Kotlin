package com.example.saurabh.userappmvp.datasource.local

import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.app.Constant.PaperDb.USER_LIST
import com.example.saurabh.userappmvp.datasource.model.User
import io.paperdb.Paper
import io.reactivex.Single
import java.lang.Exception
import javax.inject.Inject

@Suppress("NOTHING_TO_INLINE")
class LocalDbHelper @Inject constructor() : LocalDataSourceContract{
    override fun saveList(list: MutableList<User>): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(id: Int): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private infix fun save(userList : MutableList<User>) = Paper.book().write(Constant.PaperDb.USER_LIST,userList)

    private inline fun performTaskOn(user : User, task : (Int,MutableList<User>) -> Unit) : Single<User> {

        val users = Paper.book().read(Constant.PaperDb.USER_LIST, mutableListOf<User>())
        val index = users.indexOf(user)
        task.invoke(index,users)

        return Single.just(user)

    }

    override infix fun deleteUser(user: User): Single<User> {
        return performTaskOn(user){ _ ,users ->
            users.remove(user)
            this save users
        }
    }

    override infix fun addUser(user: User): Single<User> {
        return performTaskOn(user){ _ ,users ->
            users.add(user)
            this save users
        }
    }

    override fun fetchUserList(): Single<MutableList<User>> {
        return Single.just(Paper.book().read(USER_LIST, mutableListOf()))
    }

    override infix fun updateUser(user: User) : Single<User> {
        return  performTaskOn(user){ index,users ->
            users[index] = user
            this save users
        }
    }



}