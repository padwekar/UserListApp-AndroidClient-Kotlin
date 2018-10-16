package com.example.saurabh.userappmvp.userlist.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saurabh.userappmvp.datasource.model.User
import javax.inject.Inject

@Suppress("NOTHING_TO_INLINE")
class UserListAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolder>() {

    var userList = mutableListOf<User>()
    set(_) {
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        TODO("To be implemented")
        //return ViewHolder(ItemUserBinding.inflate(inflater,viewGroup,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
       // holder bind userAt(pos)
    }

    inline fun userAt(position : Int) = userList[position]

    fun setData(list :MutableList<User>){
        userList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = userList.size
}

class ViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {
     infix fun bind(user : User){
        // binding.setVariable(BR.user,user)
       //  binding.executePendingBindings()
     }
}
