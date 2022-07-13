package com.edag.uservehiclemanagement.ui.roles

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edag.uservehiclemanagement.databinding.ListViewItemBinding
import com.edag.uservehiclemanagement.network.UserRole


class RolesAdapter() :
    ListAdapter<UserRole, RolesAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            ListViewItemBinding.inflate(adapterLayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class ItemViewHolder(var binding: ListViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userRole: UserRole) {
            binding.userData = userRole
            binding.executePendingBindings()
            Log.d("test", "Item ${userRole.userMail} addedd")
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<UserRole>() {

        override fun areItemsTheSame(oldItem: UserRole, newItem: UserRole): Boolean {
            return oldItem.userMail == newItem.userMail
        }

        override fun areContentsTheSame(oldItem: UserRole, newItem: UserRole): Boolean {
            return oldItem.userRole == newItem.userRole
        }

    }
}

