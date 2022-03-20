package com.example.pertemuan6_71190414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnFirst = findViewById<Button>(R.id.buttonFirst)
        btnFirst.setOnClickListener{
            val j = Intent(this, SecondActivity::class.java)
            startActivity(j)
        }
    }
}