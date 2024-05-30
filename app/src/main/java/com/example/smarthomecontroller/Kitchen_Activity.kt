package com.example.smarthomecontroller

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Kitchen_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kitchen)

        findViewById<ToggleButton>(R.id.kitchenmicro).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("microwavekitchen", isChecked)
        }

        findViewById<ToggleButton>(R.id.kitchenchimney).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("chimneykitchen", isChecked)
        }
        getDeviceStatus( false)
        getDeviceStatus( false)
        getDeviceStatus( false)

        insertDevice(Device("kitchen","microwavekitchen", false))
        insertDevice(Device("kitchen", "chimneykitchen",false))
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