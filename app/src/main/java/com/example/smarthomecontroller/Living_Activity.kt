package com.example.smarthomecontroller

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Living_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.livingroom)

        findViewById<ToggleButton>(R.id.livinglight).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("Light", isChecked)
        }

        findViewById<ToggleButton>(R.id.livingfan).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("Fan", isChecked)
        }

        findViewById<ToggleButton>(R.id.livingtv).setOnCheckedChangeListener { _, isChecked ->
            toggleDeviceStatus("TV", isChecked)
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
}
