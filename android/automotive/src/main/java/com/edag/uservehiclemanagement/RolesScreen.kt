package com.edag.uservehiclemanagement

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action.BACK
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.edag.uservehiclemanagement.MainService.Data.rolesStatus
import com.edag.uservehiclemanagement.MainService.Data.userRoles


class RolesScreen(carContext: CarContext) : Screen(carContext), DefaultLifecycleObserver {
    override fun onGetTemplate(): Template {

        val paneBuilder = Pane.Builder()

        when (rolesStatus.value) {
            RolesApiStatus.LOADING -> paneBuilder.setLoading(true)
            RolesApiStatus.ERROR -> {
                paneBuilder.addRow(
                    Row.Builder().setTitle("Network Error!").build()
                )
            }

            else -> {
                userRoles.forEach {
                    val row = Row.Builder()
                        .setTitle(it.userMail)
                        .addText(it.userRole)
                        .build()
                    paneBuilder.addRow(row)
                }
            }
        }

        return PaneTemplate.Builder(paneBuilder.build())
            .setTitle("User Roles")
            .setHeaderAction(BACK)
            .build()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        MainService().getRoles()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        MainService().getRoles()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        rolesStatus.observe(this) { invalidate() }
        MainService().getRoles()
    }

    init {
        lifecycle.addObserver(this)
    }
}