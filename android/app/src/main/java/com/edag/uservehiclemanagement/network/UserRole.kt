package com.edag.uservehiclemanagement.network

import com.squareup.moshi.Json

data class UserRole(
    @Json(name = "mailadresse")
    val userMail: String,
    @Json(name = "UserRole")
    val userRole: String
)