package com.example.saurabh.userappmvp.userlist.presentation

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import com.example.saurabh.userappmvp.extenstion.replace
import com.example.saurabh.userappmvp.userdetail.UserDetailFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.layout_error_view.*
import tr.xip.errorview.ErrorView
import javax.inject.Inject

@InRelationShipWith(R.layout.fragment_user_list)
class UserListFragment() : BaseFragment<UserContract.Presenter>(),UserContract.View {

    companion object {
        fun newInstance() = UserListFragment()
    }

    @Inject
    lateinit var repository : UserRepository

    lateinit var adapter : UserListAdapter


    init {
        DaggerUserComponent.create().inject(this)
    }

    override fun createPresenter() = UserListPresenter(this,repository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserListAdapter(presenter)
        backButtonEnabled(false)

        recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@UserListFragment.context)
            layoutManager = linearLayoutManager
            adapter = this@UserListFragment.adapter
            addItemDecoration(DividerItemDecoration(this.context!!,linearLayoutManager.orientation))
        }

        error_view.setRetryListener {
            presenter?.onRetry()
        }


    }

    override fun updateProgressVisibility(visibility: Int) {
        progressBar.visibility = visibility
    }

    override fun showUsers() {
        adapter.notifyDataSetChanged()
    }

    override fun showUserDetailScreen(userId: Int) {
        replace(R.id.container,UserDetailFragment.newInstance(userId))
    }


    override fun updateErrorEmptyView(title: String, message: String) {
        error_view.visibility =  when(title.isEmpty()){
            true -> View.GONE
            else -> {
                error_view.setTitle(title)
                error_view.setSubtitle(message)
                View.VISIBLE
            }
        }
    }

}