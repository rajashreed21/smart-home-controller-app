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
            toggleDeviceStatus("Light", isChecked)
        }

        findViewById<ToggleButton>(R.id.bedroomfan).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("Fan", isChecked)
        }

        findViewById<ToggleButton>(R.id.bedroomac).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("Air Conditioner", isChecked)
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
}