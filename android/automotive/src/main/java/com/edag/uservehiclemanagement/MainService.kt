package com.edag.uservehiclemanagement

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.R
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import androidx.lifecycle.MutableLiveData
import com.edag.uservehiclemanagement.MainService.Data.rolesStatus
import com.edag.uservehiclemanagement.MainService.Data.vehiclesStatus
import com.edag.uservehiclemanagement.network.UserRole
import com.edag.uservehiclemanagement.network.UserVehicle
import com.edag.uservehiclemanagement.network.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

enum class RolesApiStatus { LOADING, ERROR, DONE }
enum class VehiclesApiStatus { LOADING, ERROR, DONE }
class MainService : CarAppService() {
    object Data {
        var userRoles = listOf<UserRole>()
        var userVehicles = listOf<UserVehicle>()
        var rolesStatus = MutableLiveData<RolesApiStatus>()
        var vehiclesStatus = MutableLiveData<VehiclesApiStatus>()

    }

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onDestroy() {
        // Cancel coroutines when the service is going away.
        serviceJob.cancel()
        super.onDestroy()
    }

    @SuppressLint("PrivateResource")
    override fun createHostValidator(): HostValidator {
        return if (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            HostValidator.Builder(applicationContext)
                .addAllowedHosts(R.array.hosts_allowlist_sample)
                .build()
        }
    }

    override fun onCreateSession(): Session {
        return TestSession()
    }

    fun getRoles() {
        serviceScope.launch {
            rolesStatus.value = RolesApiStatus.LOADING
            try {
                Data.userRoles = UsersApi.retrofitService.getUsers()
                rolesStatus.value = RolesApiStatus.DONE
            } catch (e: Exception) {
                rolesStatus.value = RolesApiStatus.ERROR
                Data.userRoles = listOf()
            }

        }
    }

    fun getVehicles() {
        serviceScope.launch {
            vehiclesStatus.value = VehiclesApiStatus.LOADING
            try {
                Data.userVehicles = UsersApi.retrofitService.getVehicles()
                vehiclesStatus.value = VehiclesApiStatus.DONE

            } catch (e: Exception) {
                vehiclesStatus.value = VehiclesApiStatus.ERROR
                Data.userVehicles = listOf()
            }
        }
    }

}

class TestSession() : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return MainScreen(carContext)
    }
}
