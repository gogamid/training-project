package com.edag.uservehiclemanagement

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.edag.uservehiclemanagement.MainService.Data.userVehicles

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
        MainService().getVehicles()
    }

    override fun onGetTemplate(): Template {
        val listBuilder = ItemList.Builder()

        userVehicles.forEach {
            val row = Row.Builder()
                .setTitle(it.userMail)
                .addText(it.vehicle)
                .build()
            listBuilder.addItem(row)
        }

        return ListTemplate.Builder()
            .setTitle("User Vehicles")
            .setHeaderAction(Action.BACK)
            .setSingleList(listBuilder.build())
            .build()
    }

    init {
        lifecycle.addObserver(this)
    }
}