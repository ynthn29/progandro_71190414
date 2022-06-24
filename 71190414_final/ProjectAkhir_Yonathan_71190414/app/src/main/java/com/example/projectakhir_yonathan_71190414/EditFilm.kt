package com.example.projectakhir_yonathan_71190414

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.projectakhir_yonathan_71190414.databinding.AddFilmBinding
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class EditFilm : AppCompatActivity(){
    private lateinit var binding: AddFilmBinding
    private lateinit var firebaseAuth: FirebaseAuth

    var db = Firebase.firestore
    var dbs = Firebase.firestore
    var dbe = Firebase.firestore



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    public fun showPopup(s: String) {
        var popup = AlertDialog.Builder(this)
        popup.setTitle("Message")
        popup.setMessage(s.toString())
        var popupDialog: AlertDialog = popup.create()
        popupDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val idnya = itent.getStringExtra("theId")
        val ss:String = intent.getStringExtra("theId").toString()

        setContentView(R.layout.edit_film)

        db = FirebaseFirestore.getInstance()

        db.collection("movie").orderBy("tahun",Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ){

                    if (error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!){

                        if(dc.type == DocumentChange.Type.ADDED){
                            var test = Film()
                            test = dc.document.toObject(Film::class.java)
                            if (test.film.toString().contains(ss,ignoreCase = true)) {
                                var id = dc.document.id.toString()
                                dbs = FirebaseFirestore.getInstance()
                                dbs.collection("idnum").document("D6vUFHu171ZuCwe3cAYe").update("idnum",id)
                                break
                            }

                        }
                    }

                }

            })
        //db.collection("movie").document(id).delete()

        dbs = FirebaseFirestore.getInstance()
        dbe = FirebaseFirestore.getInstance()

        val edtJudul = findViewById<EditText>(R.id.EditJudul)
        val edtGenre = findViewById<EditText>(R.id.EditGenre)
        val edtTahun = findViewById<EditText>(R.id.EditTahun)
        val edtProduser = findViewById<EditText>(R.id.EditProduser)
        val edtPemeran = findViewById<EditText>(R.id.EditPemeran)

        var docRef = dbs.collection("idnum").document("D6vUFHu171ZuCwe3cAYe")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    edtJudul.setText(document.data?.get("idnum").toString())
                    var xid = edtJudul.text.toString()
                    var docRef2 = dbe.collection("movie").document(xid)
                    docRef2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                                edtJudul.setText(document.data?.get("film").toString())
                                edtGenre.setText(document.data?.get("genre").toString())
                                edtTahun.setText(document.data?.get("tahun").toString())
                                edtProduser.setText(document.data?.get("produser").toString())
                                edtPemeran.setText(document.data?.get("pemeran").toString())
                                //showPopup(edtJudul.text.toString())
                            } else {
                                Log.d(TAG, "No such document")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "get failed with ", exception)
                        }

                    //showPopup(edtJudul.text.toString())
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }



        val btnEdit = findViewById<Button>(R.id.btnEdit)
        btnEdit.setOnClickListener {

            var docRef = dbs.collection("idnum").document("D6vUFHu171ZuCwe3cAYe")
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        btnEdit.setText(document.data?.get("idnum").toString())
                        var xid = btnEdit.text.toString()
                        dbe = FirebaseFirestore.getInstance()
                        dbe.collection("movie").document(xid).update("film",edtJudul.text.toString())
                        dbe.collection("movie").document(xid).update("genre",edtGenre.text.toString())
                        dbe.collection("movie").document(xid).update("tahun",edtTahun.text.toString())
                        dbe.collection("movie").document(xid).update("produser",edtProduser.text.toString())
                        dbe.collection("movie").document(xid).update("pemeran",edtPemeran.text.toString())
                        btnEdit.setText("UPDATE")
                        showPopup("Data Sudah Diubah, Tekan tombol SEARCH di Halaman List untuk refresh")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }

        }



    }

}