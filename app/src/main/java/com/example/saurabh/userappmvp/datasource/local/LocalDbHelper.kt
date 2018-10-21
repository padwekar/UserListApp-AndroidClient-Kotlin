package com.example.saurabh.userappmvp.datasource.local

import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.app.Constant.PaperDb.USER_LIST
import com.example.saurabh.userappmvp.datasource.model.User
import io.paperdb.Paper
import io.reactivex.Flowable
import java.lang.Exception
import javax.inject.Inject

@Suppress("NOTHING_TO_INLINE")
class LocalDbHelper @Inject constructor() : LocalDataSourceContract{

    override fun getUser(id: Int): Flowable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private infix fun save(userList : MutableList<User>) = Paper.book().write(Constant.PaperDb.USER_LIST,userList)

    private inline fun performTaskOn(user : User, task : (Int,MutableList<User>) -> Unit) : Flowable<Boolean> {

        val users = Paper.book().read(Constant.PaperDb.USER_LIST, mutableListOf<User>())
        val index = users.indexOf(user)
        task.invoke(index,users)

        return Flowable.fromArray(true)
    }

    override infix fun deleteUser(user: User): Flowable<Boolean> {
        return performTaskOn(user){ _ ,users ->
            users.remove(user)
            this save users
        }
    }

    override infix fun addUser(user: User): Flowable<Boolean> {
        return performTaskOn(user){ _ ,users ->
            users.add(user)
            this save users
        }
    }

    override fun fetchUserList(): Flowable<MutableList<User>> {
        return Flowable.fromArray(Paper.book().read(USER_LIST, mutableListOf()))
    }

    override infix fun updateUser(user: User) : Flowable<Boolean> {
        return  performTaskOn(user){ index,users ->
            users[index] = user
            this save users
        }
    }



}