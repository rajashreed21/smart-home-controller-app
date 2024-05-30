package com.example.smarthomecontroller

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Bedroom_class : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bedroom)

        findViewById<ToggleButton>(R.id.bedroomlight).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("lightbedroom", isChecked)
        }

        findViewById<ToggleButton>(R.id.bedroomfan).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("fanbedroom", isChecked)
        }

        findViewById<ToggleButton>(R.id.bedroomac).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("acbedroom", isChecked)
        }

        getDeviceStatus( false)
        getDeviceStatus( false)
        getDeviceStatus( false)

        insertDevice(Device("bedroom","lightbedroom", false))
        insertDevice(Device("bedroom", "fanbedroom",false))
        insertDevice(Device("bedroom", "acbedroom",false))
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
            val response = RetrofitInstance.api.toggleDeviceStatus(ToggleDeviceRequest(device, status))
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