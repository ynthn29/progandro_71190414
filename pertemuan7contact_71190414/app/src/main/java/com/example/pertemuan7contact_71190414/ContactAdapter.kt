package com.example.pertemuan7contact_71190414

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ContactAdapter (val listContact: ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ContactHolder>(){
    class ContactHolder(val v: View): RecyclerView.ViewHolder(v){
        var telp: Contact? = null

        fun bindView(telp: Contact){
            this.telp = telp
            v.findViewById<TextView>(R.id.txvName).text = "${telp.name}"
            v.findViewById<TextView>(R.id.txvNumber).text = "${telp.number}"
            v.findViewById<ImageView>(R.id.imgCover).setImageResource(telp.cover).toString()
            v.setOnClickListener {
                Toast.makeText(v.context,"${telp.name} - ${telp.number}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ContactHolder{
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent,false)
        return ContactHolder(v)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindView((listContact[position]))
}

    override fun getItemCount(): Int = listContact.size
}