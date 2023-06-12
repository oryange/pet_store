package com.example.app.repository.dataLocal

internal interface PetsSharedPreferences {
    fun addFavorite(newItem: String): Boolean

    fun removeFavorite(itemToRemove: String): Boolean

    fun getFavorite(): List<String>
}
