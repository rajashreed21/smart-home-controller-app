package com.example.smarthomecontroller

data class Device(
    val name: String,
    val type: String,
    val status: Boolean
)

data class ToggleDeviceRequest(
    val device: String,
    val status: Boolean
)

data class DeviceStatusResponse(
    val status: Boolean
)
