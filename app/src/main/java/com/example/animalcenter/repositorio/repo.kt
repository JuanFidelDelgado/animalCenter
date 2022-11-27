package com.example.animalcenter.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animalcenter.model.citas
import com.example.animalcenter.model.mascotas
import com.example.animalcenter.model.usuarios
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun getMascotasData():LiveData<MutableList<mascotas>>{
        val mutableData=MutableLiveData<MutableList<mascotas>>()

        FirebaseFirestore.getInstance().collection("mascotas").get().addOnSuccessListener {
            result->
            val listData= mutableListOf<mascotas>()
            for(document in result){
                val nombre= document.getString("nombre")
                val especie= document.getString("especie")
                val raza= document.getString("raza")
                val edad= document.getString("edad")
                val foto= document.getString("foto")
                val mascota= mascotas(nombre!!, especie!!, raza!!, edad!!, foto!!)
                listData.add(mascota)
            }

            mutableData.value=listData
        }
        return mutableData
    }

    fun getCitasData():LiveData<MutableList<citas>>{
        val mutableData=MutableLiveData<MutableList<citas>>()

        FirebaseFirestore.getInstance().collection("citas").get().addOnSuccessListener {
            result->
            val listData= mutableListOf<citas>()
            for(document in result){
                val nombre= document.getString("nombre")
                val fechaCita= document.getString("fechaCita")
                val horaCita= document.getString("horaCita")
                val cita= citas(nombre!!, fechaCita!!, horaCita!!)
                listData.add(cita)
            }
            mutableData.value=listData
        }
        return mutableData
    }

    fun getUsuariosData():LiveData<MutableList<usuarios>>{
        val mutableData=MutableLiveData<MutableList<usuarios>>()

        FirebaseFirestore.getInstance().collection("usaurios").get().addOnSuccessListener {
                result->
            val listData= mutableListOf<usuarios>()
            for(document in result){
                val nombres= document.getString("nombres")
                val apellidos= document.getString("apellidos")
                val celular= document.getString("celular")
                val direccion= document.getString("direccion")
                val foto= document.getString("foto")
                val usuario= usuarios(nombres!!, apellidos!!, celular!!, direccion!!, foto!!)
                listData.add(usuario)
            }
            mutableData.value=listData
        }
        return mutableData
    }

}