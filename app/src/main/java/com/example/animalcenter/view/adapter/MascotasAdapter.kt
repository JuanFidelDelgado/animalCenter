package com.example.animalcenter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R
import com.example.animalcenter.model.mascotas
import com.squareup.picasso.Picasso
//import io.grpc.Context
import android.content.Context
import android.widget.Button
import android.widget.ImageButton

class MascotasAdapter(private val context: Context, var clickListener: OnMascotasItemClickListener): RecyclerView.Adapter<MascotasAdapter.ViewHolder>(){

    private var mascotasLista= mutableListOf<mascotas>()

    fun setListData(data: MutableList<mascotas>){
        mascotasLista=data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): ViewHolder{
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_mascotas, viewGroup,false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        fun binWeb(mascotas: mascotas, action: OnMascotasItemClickListener){
            itemView.findViewById<TextView>(R.id.nombre).text=mascotas.nombre
            itemView.findViewById<TextView>(R.id.especie).text=mascotas.especie
            itemView.findViewById<TextView>(R.id.raza).text=mascotas.raza
            itemView.findViewById<TextView>(R.id.edad).text=mascotas.edad
            Picasso.with(context).load(mascotas.foto).into(itemView.findViewById<ImageView>(R.id.foto))
            val btnCita=itemView.findViewById<TextView>(R.id.btnCita)
            btnCita.setOnClickListener {
                action.onItemClick(mascotas, adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i:Int){
        val mascotas= mascotasLista[i]
        viewHolder.binWeb(mascotas, clickListener)

    }

    override fun getItemCount(): Int{
        return if(mascotasLista.size>0){
            mascotasLista.size
        } else {
            0
        }
    }
}

interface OnMascotasItemClickListener{
    fun onItemClick(mascotas: mascotas, position: Int)
}