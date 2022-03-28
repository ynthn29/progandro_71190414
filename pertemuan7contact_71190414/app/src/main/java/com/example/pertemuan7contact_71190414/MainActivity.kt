package com.example.pertemuan7contact_71190414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = ArrayList<Contact>()
        listContact.add(Contact("Ochi","08123456789",R.mipmap.ochi))
        listContact.add(Contact("Nori","08567894541",R.mipmap.nori))
        listContact.add(Contact("Pocky","08224567818",R.mipmap.pocky))
        val rcyContact = findViewById<RecyclerView>(R.id.rcyContact)
        rcyContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rcyContact.adapter = contactAdapter


    }
}