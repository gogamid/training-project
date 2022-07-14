package com.edag.uservehiclemanagement.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edag.uservehiclemanagement.R
import com.edag.uservehiclemanagement.network.UserRole
import com.edag.uservehiclemanagement.network.UserVehicle
import com.edag.uservehiclemanagement.ui.roles.RolesAdapter
import com.edag.uservehiclemanagement.ui.roles.RolesApiStatus
import com.edag.uservehiclemanagement.ui.vehicles.VehiclesAdapter
import com.edag.uservehiclemanagement.ui.vehicles.VehiclesApiStatus


@BindingAdapter("listData")
fun bindRecyclerViewUserRole(recyclerView: RecyclerView, data: List<UserRole>?) {
    Log.d("test", "list data $data")
    val adapter = recyclerView.adapter as RolesAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerViewVehicle(recyclerView: RecyclerView, data: List<UserVehicle>?) {
    val adapter = recyclerView.adapter as VehiclesAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindRolesStatus(statusImageView: ImageView, status: RolesApiStatus?) {
    when (status) {
        RolesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RolesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("apiStatus")
fun bindVehicleStatus(statusImageView: ImageView, status: VehiclesApiStatus?) {
    when (status) {
        VehiclesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        VehiclesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}