package com.example.animalcenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalcenter.model.usuarios
import com.example.animalcenter.repositorio.repo

class usuariosViewModel:ViewModel() {
    val repo= repo()

    fun usuariosData():LiveData<MutableList<usuarios>>{
        val mutableData= MutableLiveData<MutableList<usuarios>>()
        repo.getUsuariosData().observeForever {
            mutableData.value=it
        }
        return mutableData
    }


}