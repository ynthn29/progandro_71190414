package com.example.pertemua10_sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var dbHelper: DatabaseHelper? = null
    var sql: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtUsia = findViewById<EditText>(R.id.edtUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val edtCari = findViewById<EditText>(R.id.edtCari)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val hasilCari = findViewById<TextView>(R.id.hasilCari)

        dbHelper = DatabaseHelper(this)
        sql = dbHelper?.writableDatabase

        btnSimpan.setOnClickListener{
            val values = ContentValues().apply{
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA,edtNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA,edtUsia.text.toString())

            }
            sql?.insert(DatabaseContract.Penduduk.TABLE_NAME,null,values)
            edtNama.setText("")
            edtUsia.setText("")
            refreshData()
        }

        btnHapus.setOnClickListener{
            val selection = "USIA = ? OR NAMA = ?"
            val selectionArgs = arrayOf(edtUsia.text.toString(),edtNama.text.toString())

            sql?.delete(DatabaseContract.Penduduk.TABLE_NAME,selection,selectionArgs)

            refreshData()
        }
        refreshData()

        btnCari.setOnClickListener {
            val query = " SELECT * FROM ${DatabaseContract.Penduduk.TABLE_NAME} WHERE ${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ?"
            val select = arrayOf(edtCari.text.toString())
            val cursor = sql?.rawQuery(
                query,select
            )
            var hasil = ""
            with(cursor!!){
                while(moveToNext()){
                    hasil += "${this!!.getString(1)}"

                }
            }
            hasilCari.text = hasil
            edtCari.setText("")
            refreshData()
        }
    }

    fun refreshData(){
        var txvHasil = findViewById<TextView>(R.id.hasil)
        val columns = arrayOf(BaseColumns._ID,
        DatabaseContract.Penduduk.COLUMN_NAME_NAMA, DatabaseContract.Penduduk.COLUMN_NAME_USIA)

        val cursor = sql?.query(
            DatabaseContract.Penduduk.TABLE_NAME,columns,null,null,null,null,null
        )

        var result = ""
        with(cursor){
            while(this!!.moveToNext()){
                result += "${this!!.getString(1)} - ${this!!.getString(2)}th\n"
            }
        }
        txvHasil.text = result
    }


}