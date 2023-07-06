package com.luu.android.repository

import com.luu.android.data.local.sharedpfers.SharedPrefsWrapper
import com.luu.android.model.entity.Category
import jp.btechnologies.app.data.local.sharedpfers.SharedPrefKeys

class UsersRepository(
    private val sharedPrefsApi: SharedPrefsWrapper
) {
    fun getToken(): String? {
        return sharedPrefsApi[SharedPrefKeys.KEY_TOKEN]
    }

    fun setToken(token: String) {
        sharedPrefsApi[SharedPrefKeys.KEY_TOKEN] = token
    }

    fun getCategories(): List<Category> {
        return sharedPrefsApi[SharedPrefKeys.KEY_CATEGORIES]
    }

    fun setCategories(listCategory: List<Category>) {
        sharedPrefsApi[SharedPrefKeys.KEY_CATEGORIES] = listCategory
    }

}