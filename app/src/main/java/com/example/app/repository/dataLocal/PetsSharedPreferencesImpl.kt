package com.example.app.repository.dataLocal

import android.content.Context
import com.example.app.utils.Constants.keyPreferences
import com.example.app.utils.Constants.keySheredPreferences

class PetsSharedPreferencesImpl(context: Context) : PetsSharedPreferences {
    private val sharedPreferences =
        context.getSharedPreferences(keySheredPreferences, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val itemList = sharedPreferences.getStringSet(keyPreferences, mutableSetOf())

    override fun addFavorite(newItem: String): Boolean {
        itemList?.add(newItem)
        editor.putStringSet(keyPreferences, itemList).apply()
        return true
    }

    override fun removeFavorite(itemToRemove: String): Boolean {
        itemList?.remove(itemToRemove)
        editor.putStringSet(keyPreferences, itemList)
        editor.apply()
        return true
    }

    override fun getFavorite(): List<String> = itemList?.toList() ?: emptyList()
}
