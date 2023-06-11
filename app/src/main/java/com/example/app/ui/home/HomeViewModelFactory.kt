package com.example.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.repository.PetsRepository

internal class HomeViewModelFactory(private val petsRepository: PetsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(petsRepository) as T
        }
        throw IllegalArgumentException("Unknown HomeViewModel class")
    }
}

