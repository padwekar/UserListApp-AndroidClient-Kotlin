package com.example.saurabh.userappmvp.updateuser

import android.os.Bundle
import com.example.saurabh.userappmvp.app.Constant
import com.example.saurabh.userappmvp.base.BaseFragment

class UserAddUpdateFragment : BaseFragment<UserAddUpdatePresenter>(), UserAddUpdateContract {

    companion object {
        fun newInstance(userId : Int = -1) = UserAddUpdateFragment().apply {
            arguments = Bundle().apply {
                putInt(Constant.Bundle.USER_ID,userId)
            }
        }
    }
}