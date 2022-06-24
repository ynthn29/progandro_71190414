package com.example.projectakhir_yonathan_71190414

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.projectakhir_yonathan_71190414.databinding.ActivityHalamanFilmBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HalamanSearch : AppCompatActivity() {
    private lateinit var binding: ActivityHalamanFilmBinding
    private lateinit var movieArrayList: ArrayList<Film>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var myAdapter: FilmAdapter
    var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val edtSearch = findViewById<EditText>(R.id.edtSearch)
    }
}