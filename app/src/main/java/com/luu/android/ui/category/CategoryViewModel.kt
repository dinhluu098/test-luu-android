package com.luu.android.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luu.android.model.entity.Category
import com.luu.android.model.response.SigninResponse
import com.luu.android.model.response.SignupResponse
import com.luu.android.repository.AppRepository
import com.luu.android.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CategoryViewModel(
    private val appRepository: AppRepository,
    val usersRepository: UsersRepository
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    private val _fetchListCategorySuccess = MutableLiveData<List<Category>>()
    val fetchListCategorySuccess: LiveData<List<Category>> get() = _fetchListCategorySuccess

    fun fetchListCategory() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    appRepository.fetchListCategory()
                }
                _fetchListCategorySuccess.value = response
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        errorMessage.postValue("Network Error")
                    }

                    is HttpException -> {
                        val codeError = throwable.code()
                        val errorMessageResponse = throwable.message()
                        errorMessage.postValue("Error $errorMessageResponse : $codeError")
                    }

                    else -> {
                        errorMessage.postValue("Uknown error")
                    }
                }
            }
        }
    }
}