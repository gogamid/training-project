package com.edag.uservehiclemanagement.ui.roles

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.edag.uservehiclemanagement.R
import com.edag.uservehiclemanagement.databinding.FragmentRolesBinding

class RolesFragment : Fragment() {
    private val viewModel: RolesViewModel by viewModels()
    private lateinit var binding: FragmentRolesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRolesBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@RolesFragment
            rolesViewModel = viewModel
            rolesRecyclerView.adapter = RolesAdapter()
        }
        return binding.root
    }
}