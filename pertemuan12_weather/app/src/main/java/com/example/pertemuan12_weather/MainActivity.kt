package com.example.pertemuan12_weather

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtKota = findViewById<EditText>(R.id.edtKota)
        val btnCek = findViewById<Button>(R.id.btnCek)
        val txvHasil = findViewById<TextView>(R.id.txvHasil)


        txvHasil.movementMethod = ScrollingMovementMethod()
        btnCek.setOnClickListener{
            Cuaca(edtKota.text.toString())
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun convertLongToTime(time: Long): String {
        val xx = java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(time)).toString()
        return xx
        //return Date(Timestamp(time).time).toString()
    }
    fun Cuaca(kota: String){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${kota}&appid=c0f7287645f3e9953398c95607d44d79"
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val data = JSONObject(response)
                val txvHasil = findViewById<TextView>(R.id.txvHasil)
                txvHasil.setText("")
                for (i in 0..19) {
                    val dt = data.getJSONArray("list").getJSONObject(i).getLong("dt")
                    val waktu = convertLongToTime(dt.toLong())

                    val suhu = data.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp")
                    txvHasil.text = txvHasil.text.toString() + "${waktu} suhu : ${String.format("%.2f",suhu-273.15).toDouble()}\u2103\n"

                }

            },
            {

            }
        )
        queue.add(request)
    }
}