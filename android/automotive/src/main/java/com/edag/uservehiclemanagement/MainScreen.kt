package com.edag.uservehiclemanagement

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.ItemList
import androidx.car.app.model.ListTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class MainScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        val rolesRow = Row.Builder()
            .setOnClickListener { navigateToRoles() }
            .setTitle("Roles")
            .addText("Get the roles of users")
            .build()

        val vehiclesRow = Row.Builder()
            .setOnClickListener { navigateToVehicles() }
            .setTitle("Vehicles")
            .addText("Get the vehicles of the users")
            .build()

        val listBuilder = ItemList.Builder()
            .addItem(rolesRow)
            .addItem(vehiclesRow)

        return ListTemplate.Builder()
            .setSingleList(listBuilder.build())
            .setTitle("Users")
            .build()
    }

    private fun navigateToVehicles() {
        screenManager.push(VehiclesScreen(carContext))

    }

    private fun navigateToRoles() {
        screenManager.push(RolesScreen(carContext))
    }

}