package com.checkfer.randomusers.ui.userList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.checkfer.randomusers.data.model.User
import com.checkfer.randomusers.databinding.ViewUsersListItemBinding

class UserListAdapter(
    private val users: List<User>,
    private val itemClickCallback: (user: User) -> Unit
) : RecyclerView.Adapter<UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewUsersListItemBinding.inflate(inflater, parent, false)
        return UserListViewHolder(binding, itemClickCallback)
    }

    override fun onBindViewHolder(viewHolder: UserListViewHolder, position: Int) {
        val user = users[position]
        viewHolder.bind(user)
    }

    override fun getItemCount() = users.size
}