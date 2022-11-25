package com.example.animalcenter.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R
import com.example.animalcenter.model.citas

class CitasAdapter(private val context: Context): RecyclerView.Adapter<CitasAdapter.ViewHolder>() {

    private var citasLista= mutableListOf<citas>()

    fun setListData(data:MutableList<citas>){
        citasLista=data
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i:Int): CitasAdapter.ViewHolder {
        val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_citas, viewGroup,false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView:View): RecyclerView.ViewHolder(ItemView){
        //Esta funcion trae de la BD la información
        fun binWeb(cita: citas){
            itemView.findViewById<TextView>(R.id.nombre).text=cita.nombre
            itemView.findViewById<TextView>(R.id.fecha).text=cita.edad
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cita=citasLista[position]
        viewHolder.binWeb(cita)
    }

    override fun getItemCount(): Int {
        return if(citasLista.size>0){
            citasLista.size
        } else {
            0
        }
    }
}