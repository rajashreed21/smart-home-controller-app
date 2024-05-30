package com.example.smarthomecontroller

data class Device(
    val room: String,
    val devicename: String,
    val status: Boolean
)

data class ToggleDeviceRequest(
    val device: String,
    val status: Boolean
)

data class DeviceStatusResponse(
    val status: Boolean
)
