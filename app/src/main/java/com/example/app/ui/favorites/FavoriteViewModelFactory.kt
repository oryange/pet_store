package com.example.app.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app.repository.dataLocal.PetsSharedPreferences

internal class FavoriteViewModelFactory( private val petsSharedPreferences: PetsSharedPreferences) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(petsSharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown HomeViewModel class")
    }
}
