package com.example.saurabh.userappmvp.userlist.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.extenstion.replace
import com.example.saurabh.userappmvp.updateuser.UserAddUpdateFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.layout_error_view.*
import javax.inject.Inject

@InRelationShipWith(R.layout.fragment_user_list)
class UserListFragment() : BaseFragment<UserContract.Presenter>(),UserContract.View {

    companion object {
        fun newInstance() = UserListFragment()
    }

    @Inject
    lateinit var repository : UserRepository

    @Inject
    lateinit var adapter : UserListAdapter

    override fun createPresenter() = UserListPresenter(this,repository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this@UserListFragment.context)
        recyclerView.adapter = adapter
    }

    override fun showProgress(visible: Boolean) {
        progressBar.visibility = when(visible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    override fun showUsers(users : MutableList<User>) {
        adapter.userList = users
    }

    override fun showUserDetailScreen(userId: Int) {
        replace(R.id.center,UserAddUpdateFragment.newInstance(userId))
    }

    override fun showAddUserScreen() {
        replace(R.id.center,UserAddUpdateFragment.newInstance())
    }

    override fun updateErrorEmptyView(title: String, message: String) {
        error_view.visibility =  when(title.isEmpty()){
            true -> View.GONE
            else -> {
                error_view.title = title
                error_view.subtitle = message
                View.VISIBLE
            }
        }
    }

}