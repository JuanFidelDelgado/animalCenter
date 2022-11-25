package com.example.animalcenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalcenter.model.mascotas
import com.example.animalcenter.repositorio.repo

class mascotasViewModel:ViewModel() {
    val repo= repo()

    fun mascotasData():LiveData<MutableList<mascotas>>{
        val mutableData= MutableLiveData<MutableList<mascotas>>()
        repo.getMascotasData().observeForever {
            mutableData.value=it
        }
        return mutableData
    }

}