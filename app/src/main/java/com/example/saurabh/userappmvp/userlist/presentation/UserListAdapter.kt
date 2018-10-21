package com.example.saurabh.userappmvp.userlist.presentation

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.example.saurabh.userappmvp.databinding.ItemUserBinding
import com.example.saurabh.userappmvp.datasource.model.User
import javax.inject.Inject

@Suppress("NOTHING_TO_INLINE")
class UserListAdapter (val presenter: UserContract.Presenter?) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(presenter,ItemUserBinding.inflate(inflater,viewGroup,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder bind userAt(pos)
    }

    inline fun userAt(position : Int) = presenter?.userAtPosition(position) ?: User()

    override fun getItemCount() : Int = presenter?.totalItemCount ?: 0

}

class ViewHolder(val presenter: UserContract.Presenter?,val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
     infix fun bind(user : User){
         binding.setVariable(BR.user,user)
         binding.executePendingBindings()
         binding.root.setOnClickListener {
             presenter?.onUserClicked(user)
         }
     }

}
