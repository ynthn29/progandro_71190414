package com.example.pertemuan6_71190414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val btnThird = findViewById<Button>(R.id.buttonThird)
        btnThird.setOnClickListener{
            val j = Intent(this, MainActivity::class.java)
            startActivity(j)
        }
    }
}