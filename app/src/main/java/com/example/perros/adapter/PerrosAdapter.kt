package com.example.perros.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perros.R

class PerrosAdapter(private val imagenes:List<String>):RecyclerView.Adapter<PerroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PerroViewHolder(layoutInflater.inflate(R.layout.perro_renglon,parent,false))
    }

    override fun onBindViewHolder(holder: PerroViewHolder, position: Int) {
        val perrourl=imagenes[position]
        holder.bind(perrourl)
    }

    override fun getItemCount(): Int {
        return imagenes.size
    }

}