package com.edag.uservehiclemanagement.network

import com.squareup.moshi.Json

data class UserVehicle(
    @Json(name = "mailadresse")
    val userMail: String,
    @Json(name = "Vehicle")
    val vehicle: String
)