package com.example.pertemuan5_71190414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val message: TextView = findViewById(R.id.messageText)
        val user = intent.getStringExtra("nama")
        message.text = "Selamat Datang $user"

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener{
            val j: Intent = Intent(this,LoginActivity::class.java)
            startActivity(j)
        }
    }
}