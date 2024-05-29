
package com.example.smarthomecontroller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Dashboard_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val livingroombtn = findViewById<ImageButton>(R.id.living)
        val bedroombtn = findViewById<ImageButton>(R.id.bedroom)
        val kitchenbtn = findViewById<ImageButton>(R.id.kitchen)

        livingroombtn.setOnClickListener {

            val intent = Intent(this, Living_Activity::class.java)
            startActivity(intent)
        }

        bedroombtn.setOnClickListener {

            val intent = Intent(this, Bedroom_class::class.java)
            startActivity(intent)
        }

        kitchenbtn.setOnClickListener {

            val intent = Intent(this, Kitchen_Activity::class.java)
            startActivity(intent)
        }
    }
}