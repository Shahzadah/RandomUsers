package com.checkfer.randomusers.ui.userList.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.checkfer.randomusers.R
import com.checkfer.randomusers.data.extensions.formattedLocation
import com.checkfer.randomusers.data.extensions.formattedName
import com.checkfer.randomusers.data.model.User
import com.checkfer.randomusers.databinding.ViewUsersListItemBinding

class UserListViewHolder(
    private val binding: ViewUsersListItemBinding,
    private val itemClickCallback: (user: User) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        binding.textViewName.text = user.name.formattedName()
        binding.textViewLocation.text = user.location.formattedLocation()

        Glide.with(binding.imageViewProfilePic)
            .load(user.picture.medium)
            .placeholder(R.drawable.image_placeholder)
            .error(android.R.drawable.stat_notify_error)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.imageViewProfilePic)

        binding.constraintLayoutUserItem.setOnClickListener { itemClickCallback.invoke(user) }
    }
}