package com.example.projectakhir_yonathan_71190414

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.projectakhir_yonathan_71190414.databinding.AddFilmBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddFilm : AppCompatActivity(){
    private lateinit var binding: AddFilmBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_film)

        db = FirebaseFirestore.getInstance()

        val edtJudul = findViewById<EditText>(R.id.AddJudul)
        val edtGenre = findViewById<EditText>(R.id.AddGenre)
        val edtTahun = findViewById<EditText>(R.id.AddTahun)
        val edtProduser = findViewById<EditText>(R.id.AddProduser)
        val edtPemeran = findViewById<EditText>(R.id.AddPemeran)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val film = Film(edtJudul.text.toString(),edtGenre.text.toString(),edtTahun.text.toString(),edtProduser.text.toString(),edtPemeran.text.toString(),"1")

            db.collection("movie").add(film)
            startActivity(Intent(this,HalamanFilm::class.java))

        }

    }

}