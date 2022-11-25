package com.example.animalcenter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animalcenter.R

class PerfilAdapter: RecyclerView.Adapter<PerfilAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, int: Int): ViewHolder{
        val v=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_view_perfil, viewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){

        var itemFotoPerfil: ImageView
        var itemNombres: TextView
        var itemApellidos: TextView
        var itemCelular: TextView
        var itemEmail: TextView
        var itemDireccion: TextView

        init {
            itemFotoPerfil= itemView.findViewById(R.id.fotoPerfil)
            itemNombres=ItemView.findViewById(R.id.nombres)
            itemApellidos=ItemView.findViewById(R.id.apellidos)
            itemCelular=ItemView.findViewById(R.id.celular)
            itemEmail=ItemView.findViewById(R.id.email)
            itemDireccion=ItemView.findViewById(R.id.direccion)
        }
    }

    var fotoPerfil = arrayOf(R.drawable.foto_perfil_1)
    var nombres = arrayOf("Juan Fidel")
    var apellidos = arrayOf("Delgado González")
    var celular = arrayOf("057-3112567894")
    var email = arrayOf("delgadojuanfidel@gmail.com")
    var direccion = arrayOf("Calle 1 # 11 - 91")

    override fun onBindViewHolder(viewHolder: PerfilAdapter.ViewHolder, i:Int){
        //viewHolder.itemFotoPerfil.setImageResource(fotoPerfil[i])
        viewHolder.itemNombres.text=nombres[i]
        viewHolder.itemApellidos.text=apellidos[i]
        viewHolder.itemCelular.text=celular[i]
        viewHolder.itemEmail.text=email[i]
        viewHolder.itemDireccion.text=direccion[i]

    }

    override fun getItemCount(): Int{
        return nombres.size
    }

}