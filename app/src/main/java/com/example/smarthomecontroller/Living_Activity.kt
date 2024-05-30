package com.example.smarthomecontroller

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Living_Activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.livingroom)

        findViewById<ToggleButton>(R.id.livingdoor).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("doorlivingroom", isChecked)
        }

        findViewById<ToggleButton>(R.id.livingmusic).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("musicsystemlivingroom", isChecked)
        }

        findViewById<ToggleButton>(R.id.livingtv).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("tvlivingroom", isChecked)
        }
        getDeviceStatus( false)
        getDeviceStatus( false)
        getDeviceStatus( false)

        insertDevice(Device("livingroom","doorlivingroom", false))
        insertDevice(Device("livingroom", "musicsystemlivinroom",false))
        insertDevice(Device("livingroom", "tvlivingroom",false))
    }

    private fun insertDevice(device: Device) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                RetrofitInstance.api.insertDevice(device)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Handle success
                } else {
                    // Handle error
                }
            }
        }
    }

    private fun toggleDeviceStatus(device: String, status: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =
                RetrofitInstance.api.toggleDeviceStatus(ToggleDeviceRequest(device, status))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    // Handle success
                } else {
                    // Handle error
                }
            }
        }
    }
    private fun getDeviceStatus(status: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getDeviceStatus(status)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.let{statusResponse->
                        statusResponse.status
                    }
                    // Handle success
                } else {
                    // Handle error
                }
            }
        }
    }
}
