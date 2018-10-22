package com.example.saurabh.userappmvp.updateuser

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.*
import com.example.saurabh.userappmvp.R
import com.example.saurabh.userappmvp.app.Constant.Bundle.USER_PARCEALBLE
import com.example.saurabh.userappmvp.base.BaseFragment
import com.example.saurabh.userappmvp.databinding.FragmentEditUserBinding
import com.example.saurabh.userappmvp.datasource.UserRepository
import com.example.saurabh.userappmvp.datasource.annotation.InRelationShipWith
import com.example.saurabh.userappmvp.datasource.model.Gender
import com.example.saurabh.userappmvp.datasource.model.User
import com.example.saurabh.userappmvp.dependency.DaggerUserComponent
import kotlinx.android.synthetic.main.fragment_edit_user.*
import javax.inject.Inject

@InRelationShipWith(R.layout.fragment_edit_user)
class EditUserFragment : BaseFragment<EditUserPresenter>(), EditUserContract.View {


    override fun showSnackMessage(message: String) {
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT).show()
    }

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
        setHasOptionsMenu(true);
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        activity?.menuInflater?.inflate(R.menu.user_save_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item!!.itemId) {
        R.id.saveMenuItem -> {presenter?.onSaveClick(); true}
        else -> super.onOptionsItemSelected(item)
    }

    override fun getFieldEntriesAsUsers() = User().apply {
        id = user.id
        name = nameEditText.text.toString()
        age = ageEditText.text.toString().toInt()
        gender = when(genderRadioGroup.checkedRadioButtonId){
            R.id.maleRadioButton -> Gender.MALE
            R.id.femaleRadioButton -> Gender.FEMALE
            else -> Gender.TRANSGENDER
        }
        description = "${name} says something something"
    }

}