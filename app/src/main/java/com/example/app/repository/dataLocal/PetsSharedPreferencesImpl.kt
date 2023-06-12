package com.example.app.repository.dataLocal

import android.content.Context
import android.util.Log


class PetsSharedPreferencesImpl(context: Context) : PetsSharedPreferences {
    //todo: remove hardcode
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    //    private val itemList: MutableSet = mutableSetOf()
    val itemList = sharedPreferences.getStringSet("item_list", mutableSetOf())

    override fun addFavorite(newItem: String): Boolean {
        itemList?.add(newItem)
        editor.putStringSet("item_list", itemList).apply()
        return true
    }

    override fun removeFavorite(itemToRemove: String): Boolean {
        itemList?.remove(itemToRemove)
        editor.putStringSet("item_list", itemList)
        editor.apply()
        return true
    }

    override fun getFavorite(): List<String> = itemList?.toList() ?: emptyList()

}
