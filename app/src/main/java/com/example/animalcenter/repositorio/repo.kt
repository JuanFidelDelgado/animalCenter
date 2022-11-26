package com.example.animalcenter.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animalcenter.model.citas
import com.example.animalcenter.model.mascotas
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
                val mascota= mascotas(nombre!!, especie!!, raza!!, edad!!)
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

}