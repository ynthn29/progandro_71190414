package com.example.projectakhir_yonathan_71190414

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter (val listFilm: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.FilmHolder>(){
    class FilmHolder(val f: View): RecyclerView.ViewHolder(f){
        var movie: Film? = null
        private var onClickListener : OnClickListener? = null

        fun setOnClickListener(onClickListener: OnClickListener){
            this.onClickListener = onClickListener
        }

        interface OnClickListener {
            fun onClick(position: Int){
            }
        }
        //private var btnDeleteEvent: Button = f.findViewById(R.id.btnDelete)
        //val delButton = f.findViewById<Button>(R.id.btnDelete)
/*        val btnDelete = f.findViewById<Button>(R.id.btnDelete)
        init {
            delButton.setOnClickListener {
                //val judulnya = f.findViewById<TextView>(R.id.txvJudul).text.toString()

                //android.content.Intent intent = new Intent(context, HalamanFilm.class)


            }
        }*/



        fun bindView(film: Film, index: Int){


            this.movie = film
            f.findViewById<TextView>(R.id.txvJudul).text = "${film.film}"
            f.findViewById<TextView>(R.id.txvGenre).text = "${film.genre}"
            f.findViewById<TextView>(R.id.txvTahun).text = "${film.tahun}"
            f.findViewById<TextView>(R.id.txvProduser).text = "${film.produser}"
            f.findViewById<TextView>(R.id.txvPemeran).text = "${film.pemeran}"
//            f.findViewById<Button>(R.id.btnDelete).setOnClickListener {
//                val judulnya = "${film.film}"
//
//            }





//            movie?.let { f.findViewById<ImageView>(R.id.filmCover).setImageResource(it.cover).toString() }

        }

        private fun deleteEvent(film: Film) {

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val f = LayoutInflater.from(parent.context).inflate(R.layout.list_film,parent,false)
        return FilmHolder(f)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bindView(listFilm[position], position)

    }

    override fun getItemCount(): Int = listFilm.size

}