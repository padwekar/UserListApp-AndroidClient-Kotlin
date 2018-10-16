package com.example.saurabh.userappmvp.updateuser

import android.os.Bundle
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith

@InRelationShipWith(R.layout.fragment_user_list)
class UserAddUpdateFragment : BaseFragment<UserAddUpdatePresenter>(), UserAddUpdateContract {

    override fun createPresenter(): UserAddUpdatePresenter {
        return UserAddUpdatePresenter()
    }

    companion object {
        fun newInstance(userId : Int = -1) = UserAddUpdateFragment().apply {
            arguments = Bundle().apply {
                putInt(Constant.Bundle.USER_ID,userId)
            }
        }
    }
}