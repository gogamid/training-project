package com.edag.uservehiclemanagement.ui.roles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edag.uservehiclemanagement.network.UserRole
import com.edag.uservehiclemanagement.network.UsersApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class RolesApiStatus { LOADING, ERROR, DONE }

class RolesViewModel : ViewModel() {

    private val _status = MutableLiveData<RolesApiStatus>()
    val status: LiveData<RolesApiStatus> = _status

    private val _userRoles = MutableLiveData<List<UserRole>>()
    val userRoles: LiveData<List<UserRole>> = _userRoles


    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
            _status.value = RolesApiStatus.LOADING
            try {
                _userRoles.value = UsersApi.retrofitService.getUsers()
                _status.value = RolesApiStatus.DONE
                Log.d("test", "api call successfull ${_userRoles.value}")
            } catch (e: Exception) {
                _status.value = RolesApiStatus.ERROR
                _userRoles.value = listOf()
                Log.d("test", "api call not successfull")
            }

        }
    }
}