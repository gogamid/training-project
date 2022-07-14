package com.edag.uservehiclemanagement.ui.vehicles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edag.uservehiclemanagement.databinding.ListViewItem1Binding

import com.edag.uservehiclemanagement.network.UserVehicle


class VehiclesAdapter :
    ListAdapter<UserVehicle, VehiclesAdapter.ItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            ListViewItem1Binding.inflate(adapterLayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


    class ItemViewHolder(var binding: ListViewItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userVehicle: UserVehicle) {
            binding.userData = userVehicle
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<UserVehicle>() {

        override fun areItemsTheSame(oldItem: UserVehicle, newItem: UserVehicle): Boolean {
            return oldItem.userMail == newItem.userMail
        }

        override fun areContentsTheSame(oldItem: UserVehicle, newItem: UserVehicle): Boolean {
            return oldItem.vehicle == newItem.vehicle
        }

    }
}

