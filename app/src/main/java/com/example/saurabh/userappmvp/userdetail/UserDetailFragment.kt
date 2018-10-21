package com.example.saurabh.userappmvp.userdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.databinding.FragmentUserDetailBinding
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.layout_error_view.*
import javax.inject.Inject


@InRelationShipWith(R.layout.fragment_user_detail)
class UserDetailFragment : BaseFragment<UserDetailContract.Presenter>(), UserDetailContract.View {

    companion object {
        fun newinstance()  = UserDetailFragment()
    }

    var userId: Int = 0

    @Inject
    lateinit var repository: UserRepository

    lateinit var binding: FragmentUserDetailBinding

    init {
        DaggerUserComponent.create().inject(this)
        userId = arguments?.getInt("userId", 1) ?: 1
    }


    override fun createPresenter() = UserDetailPresenter(userId, this, repository)

    override fun updateProgressVisibility(visibility: Int) {
        progressBar.visibility = visibility
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun updateEmptyView(emptyTitle: String, emptyMessage: String, retry: () -> Unit) {
        error_view.visibility = when (emptyTitle.isEmpty()) {
            true -> View.GONE
            else -> {
                error_view.title = emptyTitle
                error_view.subtitle = emptyMessage
                View.VISIBLE
            }
        }

    }

    override fun bindData(user: User) {
        binding.user = user
        dataSectionGroup.visibility = View.VISIBLE
    }

    override fun openEditScreen(user: User) {
        TODO("not implemented")
    }


}
