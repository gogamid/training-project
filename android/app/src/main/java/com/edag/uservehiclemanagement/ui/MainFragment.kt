package com.edag.uservehiclemanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.edag.uservehiclemanagement.R
import com.edag.uservehiclemanagement.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.rolesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_rolesFragment)
        }
        binding.vehiclesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vehiclesFragment)
        }
        return binding.root
    }
}