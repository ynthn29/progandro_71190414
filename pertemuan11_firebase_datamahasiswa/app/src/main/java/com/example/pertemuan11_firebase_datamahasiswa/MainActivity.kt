package com.example.pertemuan11_firebase_datamahasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db =  FirebaseFirestore.getInstance()

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)


        btnSimpan.setOnClickListener{
            val mhs = Mahasiswa(edtNama.text.toString(),edtNim.text.toString().toInt(),edtIpk.text.toString().toDouble())
            db.collection("mahasiswa").add(mhs)
//            db.collection("penduduk").document(edtNama.text.toString()).set(penduduk)
            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            refreshData()
        }
        refreshData()

        btnRefresh.setOnClickListener {
            refreshData()
        }
    }
    fun refreshData(){
        val radioAsc = findViewById<RadioButton>(R.id.asc)
        val radioDesc = findViewById<RadioButton>(R.id.desc)
        if(radioAsc.isChecked){
            db.collection("mahasiswa")
                .orderBy("nim", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener{result ->
                    val txvhasil = findViewById<TextView>(R.id.hasil)
                    var hasil = ""
                    for (doc in result){
                        hasil += "${doc.get("nama")} - ${doc.get("nim")} - ${doc.get("ipk")}\n"
                    }
                    txvhasil.setText(hasil)
                }
        }else if(radioDesc.isChecked){
            db.collection("mahasiswa")
                .orderBy("nim", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener{result ->
                    val txvhasil = findViewById<TextView>(R.id.hasil)
                    var hasil = ""
                    for (doc in result){
                        hasil += "${doc.get("nama")} - ${doc.get("nim")} - ${doc.get("ipk")}\n"
                    }
                    txvhasil.setText(hasil)
                }
        }

    }

    }