package com.edag.uservehiclemanagement

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.R
import androidx.car.app.Screen
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import com.edag.uservehiclemanagement.network.UserRole
import com.edag.uservehiclemanagement.network.UserVehicle
import com.edag.uservehiclemanagement.network.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainService : CarAppService() {
    object Data {
        var userRoles = listOf<UserRole>()
        var userVehicles = listOf<UserVehicle>()
    }

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onCreate() {
        super.onCreate()
        getRoles()
        getVehicles()
    }

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

     fun getVehicles() {
        serviceScope.launch {
            Data.userRoles = UsersApi.retrofitService.getUsers()
        }
    }

     fun getRoles() {
        serviceScope.launch {
            Data.userVehicles = UsersApi.retrofitService.getVehicles()
        }
    }

}

class TestSession() : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return MainScreen(carContext)
    }
}
