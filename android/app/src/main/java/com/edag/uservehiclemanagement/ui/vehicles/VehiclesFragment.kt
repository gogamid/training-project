package com.edag.uservehiclemanagement.ui.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.edag.uservehiclemanagement.databinding.FragmentVehiclesBinding


class VehiclesFragment : Fragment() {
    private val viewModel: VehiclesViewModel by viewModels()
    private lateinit var binding: FragmentVehiclesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehiclesBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@VehiclesFragment
            vehiclesViewModel = viewModel
            vehiclesRecyclerView.adapter = VehiclesAdapter()
        }
        return binding.root
    }
}