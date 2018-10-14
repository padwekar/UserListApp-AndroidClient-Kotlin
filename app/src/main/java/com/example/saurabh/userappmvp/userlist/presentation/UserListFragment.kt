package com.example.saurabh.userappmvp.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.userlist.presentation.UserContract
import com.example.saurabh.userappmvp.userlist.presentation.UserListAdapter
import com.example.saurabh.userappmvp.userlist.presentation.UserListPresenter
import kotlinx.android.synthetic.main.fragment_user_list.*

@InRelationShipWith(R.layout.fragment_user_list)
class UserListFragment : BaseFragment<UserListPresenter>(),UserContract.View{

    override fun showProgress(visible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUsers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val adapter = UserListAdapter()

    companion object {
        fun newInstance() = UserListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this@UserListFragment.context)
        recyclerView.adapter = adapter
    }



    override fun setPresenter(presenter: UserContract.Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}