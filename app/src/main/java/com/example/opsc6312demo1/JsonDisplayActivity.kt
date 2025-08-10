package com.example.opsc6312demo1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JsonDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_json)

        val btnViewJson = findViewById<Button>(R.id.btnViewJson)
        val txtViewJson = findViewById<TextView>(R.id.txtViewJson)
        val jsonData = intent.getStringExtra("json_data")

        btnViewJson.setOnClickListener {
            txtViewJson.text = jsonData ?: "No JSON data received."
        }



    }
}