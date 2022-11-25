package com.example.animalcenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animalcenter.model.citas
import com.example.animalcenter.repositorio.repo

class citasViewModel: ViewModel() {
    val repo= repo()

    fun citasData(): LiveData<MutableList<citas>> {
        val mutableData= MutableLiveData<MutableList<citas>>()
        repo.getCitasData().observeForever {
            mutableData.value=it
        }
        return mutableData
    }
}