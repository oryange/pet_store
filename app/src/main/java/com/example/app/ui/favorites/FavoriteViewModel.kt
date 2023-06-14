package com.example.app.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.repository.dataLocal.PetsSharedPreferences
import com.example.app.services.response.MessageData

internal class FavoriteViewModel(private val petsSharedPreferences: PetsSharedPreferences) :
    ViewModel() {
    private val _favoriteBreedsList = MutableLiveData<List<MessageData>>()
    val favoriteBreedsList = _favoriteBreedsList


    fun setFavoriteItem(newItem: String) {
        if (getFavorite().contains(newItem)) removeFavorite(newItem)
        else {
            addFavorite(newItem)
        }
        verifyIsFavorite()
    }

    fun verifyIsFavorite() {
        val listFavorites = getFavorite()
        listFavorites.let { list ->
            if (list.isNotEmpty()) {
                _favoriteBreedsList.value = list.map { MessageData(it, listFavorites.contains(it)) }
            } else {
                _favoriteBreedsList.value = emptyList()
            }
        }
    }

    private fun addFavorite(newItem: String): Boolean = petsSharedPreferences.addFavorite(newItem)

    private fun removeFavorite(itemToRemove: String): Boolean =
        petsSharedPreferences.removeFavorite(itemToRemove)

    private fun getFavorite(): List<String> = petsSharedPreferences.getFavorite()
}
