package com.example.perros.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.perros.databinding.PerroRenglonBinding
import com.squareup.picasso.Picasso

class PerroViewHolder (view : View) : RecyclerView.ViewHolder(view){
    private val binding=PerroRenglonBinding.bind(view)
    fun bind(imagenstring: String){
        Picasso.get().load(imagenstring).into(binding.fotoPerro)
   }

}