package com.example.app.ui.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.repository.dataLocal.PetsSharedPreferences
import com.example.app.repository.dataRemote.PetsRepository

internal class BreedListWieModelFactory(private val petsRepository: PetsRepository, private val petsSharedPreferences: PetsSharedPreferences) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedListViewModel::class.java)) {
            return BreedListViewModel(petsRepository, petsSharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown HomeViewModel class")
    }
}
