package com.edag.uservehiclemanagement.ui

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.edag.uservehiclemanagement.network.UserRole
import com.edag.uservehiclemanagement.ui.roles.RolesAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UserRole>?) {
    Log.d("test","list data $data")
    val adapter = recyclerView.adapter as RolesAdapter
    adapter.submitList(data)
}