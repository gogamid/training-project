package com.edag.uservehiclemanagement.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "http://192.168.1.217:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UsersApiService {
    @GET("users")
    suspend fun getUsers(): List<UserRole>
}

object UsersApi {
    val retrofitService: UsersApiService by lazy {
        retrofit.create(UsersApiService::class.java)
    }
}
