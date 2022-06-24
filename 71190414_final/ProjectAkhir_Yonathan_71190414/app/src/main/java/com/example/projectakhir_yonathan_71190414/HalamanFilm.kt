package com.example.projectakhir_yonathan_71190414

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectakhir_yonathan_71190414.databinding.ActivityHalamanFilmBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Document

class HalamanFilm  : AppCompatActivity() {

    private lateinit var binding: ActivityHalamanFilmBinding
    private lateinit var movieArrayList: ArrayList<Film>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var myAdapter: FilmAdapter
    var db = Firebase.firestore
    var dbs = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalamanFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        binding.btnInsert.setOnClickListener {
            startActivity(Intent(this, AddFilm::class.java))
            //finish()
        }
//        val btnDelete: Button? = findViewById<Button>(R.id.btnDelete)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val edtHapus = findViewById<EditText>(R.id.edtHapus)
        val edtEdit = findViewById<EditText>(R.id.edtEdit)



//        btnDelete.setOnClickListener {
//            db.collection("movie").whereEqualTo("judul")
//        }

//        val  listFilm = ArrayList<Film>()
//        listFilm.add(Film("Cek Toko Sebelah","Komedi","2018","Poyo","Ochi,Nori,Pocky","1"))
//        listFilm.add(Film("Ada Apa Dengan Cinta","Romansa","2020","Yona","Yonathan,Ochi,Nori","1"))
//        listFilm.add(Film("Spongebob Squarepants","Animasi","2010","Nickelodeon","Spongebob,Patrick,Squidward","1"))
//        listFilm.add(Film("KKN di Desa Penari","Horror","2022","Joko Purwanto","Badarawuh,Pak Tua,Bu Tua","1"))

        val rcyFilm = findViewById<RecyclerView>(R.id.rcyFilm)
        rcyFilm.layoutManager = LinearLayoutManager(this)
        rcyFilm.setHasFixedSize(true)

        movieArrayList = arrayListOf()

        myAdapter = FilmAdapter(movieArrayList)
        rcyFilm.adapter = myAdapter

        EventChangeListener()

        btnSearch.setOnClickListener {
            EventChangeListener()
        }

        btnHapus.setOnClickListener {
            Delete(edtHapus.text.toString())
            EventChangeListener()
            edtHapus.setText("")
        }

        btnEdit.setOnClickListener {
            val intentEdit = Intent(this, EditFilm::class.java)
            val judulnya = findViewById<EditText>(R.id.edtEdit).text.toString()
            intentEdit.putExtra("theId",judulnya)
            startActivity(intentEdit)
            edtEdit.setText("")
//            finish()
        }

       //        btnDelete?.setOnClickListener {
//            showPopup("Hello World")
//        }
    }

    private fun Delete(judul: String) {
        val judulnya = findViewById<EditText>(R.id.edtHapus).text.toString()
        var id = ""

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
                            if (test.film.toString().contains(judulnya.toString(),ignoreCase = true)) {
                                id = dc.document.id
                                dbs = FirebaseFirestore.getInstance()
                                dbs.collection("movie").document(id).delete()
                                myAdapter.notifyDataSetChanged()
                                showPopup("Data sudah dihapus, silakan refresh dengan tombol search")
                                break
                            }

                        }
                    }

                    myAdapter.notifyDataSetChanged()

                }

            })
        //db.collection("movie").document(id).delete()
        //showPopup(id)

    }

    public fun showPopup(s: String) {
        var popup = AlertDialog.Builder(this)
        popup.setTitle("Message")
        popup.setMessage(s.toString())
        var popupDialog: AlertDialog = popup.create()
        popupDialog.show()
    }

    private fun cari(judul: String) {
        db.collection("movie")
            .document(judul)
            .get()
            .addOnSuccessListener { snapshot ->
                if(snapshot != null && snapshot.exists()){
                    val doc = snapshot.data

                }
            }
    }

     fun EventChangeListener() {
        movieArrayList.clear()
        val judulnya = findViewById<EditText>(R.id.edtSearch).text.toString()

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
                            if (test.film.toString().contains(judulnya.toString(),ignoreCase = true)) {
                                movieArrayList.add(dc.document.toObject(Film::class.java))
                            }

                        }
                    }
                    myAdapter.notifyDataSetChanged()
            }

        })
    }


    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else {
            val email = firebaseUser.email
            binding.email.text = email
        }
    }


}