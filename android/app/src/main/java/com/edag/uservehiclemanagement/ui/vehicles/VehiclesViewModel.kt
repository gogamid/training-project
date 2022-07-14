package com.edag.uservehiclemanagement.ui.vehicles


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edag.uservehiclemanagement.network.UserVehicle
import com.edag.uservehiclemanagement.network.UsersApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class VehiclesApiStatus { LOADING, ERROR, DONE }

class VehiclesViewModel : ViewModel() {

    private val _status = MutableLiveData<VehiclesApiStatus>()
    val status: LiveData<VehiclesApiStatus> = _status

    private val _userVehicles = MutableLiveData<List<UserVehicle>>()
    val userVehicles: LiveData<List<UserVehicle>> = _userVehicles


    init {
        getVehicles()
    }

    private fun getVehicles() {
        viewModelScope.launch {
            _status.value = VehiclesApiStatus.LOADING
            try {
                _userVehicles.value = UsersApi.retrofitService.getVehicles()
                _status.value = VehiclesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = VehiclesApiStatus.ERROR
                _userVehicles.value = listOf()
            }

        }
    }
}