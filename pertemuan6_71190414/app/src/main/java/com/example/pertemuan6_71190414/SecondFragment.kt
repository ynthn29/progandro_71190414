package com.example.pertemuan6_71190414

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class SecondFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val v = inflater.inflate(R.layout.fragment_second,container,false)
//        val btnFirst = v.findViewById<Button>(R.id.buttonSecond)
//        btnFirst.setOnClickListener{
////            Toast.makeText(context,"Menuju first fragment",Toast.LENGTH_SHORT).show()
//            val j = Intent(context, SecondActivity::class.java)
//            context?.startActivity(j)
//        }
//        return inflater.inflate(R.layout.fragment_first,container,false)
        return v
    }
}