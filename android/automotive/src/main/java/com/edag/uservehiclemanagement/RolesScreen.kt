package com.edag.uservehiclemanagement

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action.BACK
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.edag.uservehiclemanagement.MainService.Data.userRoles


class RolesScreen(carContext: CarContext) : Screen(carContext), DefaultLifecycleObserver {
    override fun onGetTemplate(): Template {

        val listBuilder = ItemList.Builder()

        userRoles.forEach {
            val row = Row.Builder()
                .setTitle(it.userMail)
                .addText(it.userRole)
                .build()
            listBuilder.addItem(row)
        }

        return ListTemplate.Builder()
            .setTitle("User Roles")
            .setHeaderAction(BACK)
            .setSingleList(listBuilder.build())
            .build()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        MainService().getRoles()
        MainService().getVehicles()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        MainService().getRoles()
        MainService().getVehicles()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        MainService().getRoles()
        MainService().getVehicles()
    }

    init {
        lifecycle.addObserver(this)
    }
}