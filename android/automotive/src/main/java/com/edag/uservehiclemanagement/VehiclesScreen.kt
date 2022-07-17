package com.edag.uservehiclemanagement

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.edag.uservehiclemanagement.MainService.Data.userVehicles
import com.edag.uservehiclemanagement.MainService.Data.vehiclesStatus

class VehiclesScreen(carContext: CarContext) : Screen(carContext), DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        MainService().getVehicles()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        MainService().getVehicles()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        vehiclesStatus.observe(this) { invalidate() }
        MainService().getVehicles()

    }

    override fun onGetTemplate(): Template {

        val paneBuilder = Pane.Builder()

        when (vehiclesStatus.value) {
            VehiclesApiStatus.LOADING -> paneBuilder.setLoading(true)
            VehiclesApiStatus.ERROR -> {
                paneBuilder.addRow(
                    Row.Builder().setTitle("Network Error!").build()
                )
            }

            else -> {
               userVehicles.forEach {
                    val row = Row.Builder()
                        .setTitle(it.userMail)
                        .addText(it.vehicle)
                        .build()
                    paneBuilder.addRow(row)
                }
            }
        }

        return PaneTemplate.Builder(paneBuilder.build())
            .setTitle("User Vehicles")
            .setHeaderAction(Action.BACK)
            .build()
    }

    init {
        lifecycle.addObserver(this)
    }
}