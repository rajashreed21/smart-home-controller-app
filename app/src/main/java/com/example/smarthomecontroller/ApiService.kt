package com.example.smarthomecontroller

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/device")
    suspend fun insertDevice(@Body device: Device): Response<Device>

    @POST("/api/devices")
    suspend fun toggleDeviceStatus(@Body request: ToggleDeviceRequest): Response<Device>

    @GET("/api/devices/{name}")
    suspend fun getDeviceStatus(@Path("name") name: String): Response<DeviceStatusResponse>
}