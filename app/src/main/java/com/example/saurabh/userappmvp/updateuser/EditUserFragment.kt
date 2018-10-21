package com.example.saurabh.userappmvp.updateuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.app.Constant.Bundle.USER_PARCEALBLE
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.databinding.FragmentEditUserBinding
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import kotlinx.android.synthetic.main.fragment_user_detail.*
import javax.inject.Inject

@InRelationShipWith(R.layout.fragment_edit_user)
class EditUserFragment : BaseFragment<EditUserPresenter>(), EditUserContract.View {

    lateinit var binder : FragmentEditUserBinding

    @Inject
    lateinit var repository: UserRepository


    lateinit var user : User

    companion object {
        fun newInstance(user : User) = EditUserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_PARCEALBLE,user)
            }
        }
    }

    init {
        DaggerUserComponent.create().inject(this)
    }

    override fun createPresenter(): EditUserPresenter {
        user = arguments?.getParcelable<User>(USER_PARCEALBLE) ?: User()
        return EditUserPresenter(user,this,repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = FragmentEditUserBinding.inflate(layoutInflater,container,false)
        return binder.root
    }

    override fun close() {
        pop()
    }

    override fun bind(user: User) {
        binder.user = user
    }

    override fun updateProgressVisibility(visibility: Int) {
        progressBar.visibility = visibility
    }

}