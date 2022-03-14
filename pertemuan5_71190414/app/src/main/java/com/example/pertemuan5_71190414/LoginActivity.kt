package com.example.pertemuan5_71190414

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.internal.MagicApiIntrinsics

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtUsername: EditText = findViewById(R.id.edtUsername)
        val edtPassword: EditText = findViewById(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login(edtUsername.text.toString(),edtPassword.text.toString())
        }
    }

    fun login(username: String, password: String){
        if(password.equals("1234")){
            val i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("nama",username)
            startActivity(i)
        }else{
            showMessage("Password Salah")
        }
    }
    fun showMessage(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}