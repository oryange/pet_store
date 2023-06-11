package com.example.app.ui.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.repository.PetsRepository

internal class BreedListWieModelFactory(private val petsRepository: PetsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreedListViewModel::class.java)) {
            return BreedListViewModel(petsRepository) as T
        }
        throw IllegalArgumentException("Unknown HomeViewModel class")
    }
}
