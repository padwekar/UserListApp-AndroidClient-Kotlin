package com.example.saurabh.userappmvp.updateuser

import android.view.View
import com.example.saurabh.userappmvp.base.BasePresenter
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.userdetail.UserDetailContract

class EditUserPresenter (var user : User?,var view : EditUserContract.View?,var repository: UserRepository) : BasePresenter,EditUserContract.Presenter {


    private fun saveUser(){
        view?.updateProgressVisibility(View.VISIBLE)
        user?.let { it ->

            repository.remote.updateUser(it).subscribe(
                        {view?.close()
                    },{
                view?.showSnackMessage(it.localizedMessage)
                view?.updateProgressVisibility()
            }
            )
        }
    }

    override fun onStart() {
        user?.let {
            view?.bind(it)
        }
    }

    override fun onDeleteClick() {

    }

    override fun onSaveClick() {

        user = view?.getFieldEntriesAsUsers()

        (this isUserValid user!!).apply {
            if(!first){
                view?.showSnackMessage(second ?: "Something went wrong.")
                return
            }
        }

        saveUser()

    }

    private infix fun isUserValid(user : User) : Pair<Boolean,String?> {

        var isValid = true
        var message : String?= null

        fun setErrorMessage(errorMessage : String,valid : Boolean = false){
            isValid = valid
            message = errorMessage
        }


        if(user.name.isNullOrEmpty()){
            setErrorMessage("Name cannot be empty")
        } else if(user.age == null || user.age!! < 0){
            setErrorMessage("Invalid Age.")
        } else if(user.gender == null){
            setErrorMessage("Invalid Gender")
        } else if(user.description.isNullOrEmpty()){
            setErrorMessage("Description Required")
        }


        return Pair(isValid,message)
    }
}