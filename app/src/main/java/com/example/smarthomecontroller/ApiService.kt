package com.example.smarthomecontroller

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/room")
    suspend fun insertDevice(@Body device: Device): Response<Device>

    @POST("/device")
    suspend fun toggleDeviceStatus(@Body request: ToggleDeviceRequest): Response<Device>

    @GET("/room/device/{devicename}")
    suspend fun getDeviceStatus(@Path("devicename") name: Boolean): Response<DeviceStatusResponse>
}