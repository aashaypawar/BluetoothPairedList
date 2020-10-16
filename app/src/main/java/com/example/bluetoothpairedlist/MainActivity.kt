package com.example.bluetoothpairedlist

import android.bluetooth.BluetoothAdapter
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvName = findViewById<TextView>(R.id.nameTv)
        val tvMac = findViewById<TextView>(R.id.macAddressTv)
        val btn = findViewById<Button>(R.id.btnGet)
        val bAdapter = BluetoothAdapter.getDefaultAdapter()

        btn.setOnClickListener {
            if (bAdapter == null) {
                Toast.makeText(applicationContext, "Bluetooth Not Supported", Toast.LENGTH_SHORT).show()
            } else {
                val pairedDevices = bAdapter.bondedDevices
                if (pairedDevices.size > 0) {
                    for (device in pairedDevices) {
                        val deviceName = device.name
                        val macAddress = device.address
                        tvName.append("$deviceName\n")
                        tvMac.append("$macAddress\n")
                    }
                }
            }
        }
    }
}