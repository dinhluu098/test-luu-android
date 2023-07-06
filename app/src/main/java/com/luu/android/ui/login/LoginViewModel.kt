package com.luu.android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luu.android.model.entity.FormState
import com.luu.android.model.request.SigninRequest
import com.luu.android.model.request.SignupRequest
import com.luu.android.model.response.SigninResponse
import com.luu.android.model.response.SignupResponse
import com.luu.android.repository.AppRepository
import com.luu.android.repository.UsersRepository
import com.luu.android.simpeldesa.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class LoginViewModel(
    private val appRepository: AppRepository,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val errorMessage = MutableLiveData<String>()

    private val _signupSuccess = MutableLiveData<SignupResponse>()
    val signupSuccess: LiveData<SignupResponse> get() = _signupSuccess

    private val _signinSuccess = MutableLiveData<SigninResponse>()
    val signinSuccess: LiveData<SigninResponse> get() = _signinSuccess

    private val emailState = MutableStateFlow("")
    private val passwordState = MutableStateFlow("")
    private val checkboxState = MutableStateFlow(false)

    fun signup(signupRequest: SignupRequest) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    appRepository.signup(signupRequest)
                }
                _signupSuccess.postValue(response)
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

    fun signin(signinRequest: SigninRequest) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    appRepository.signin(signinRequest)
                }
                usersRepository.setToken(response.accessToken.toString())
                _signinSuccess.value = response
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

    val formState = combine(
        emailState,
        passwordState,
        checkboxState
    ) { email, password, isChecked ->
        validateFields(email, password, isChecked)
    }.distinctUntilChanged()

    fun setEmail(email: String) {
        emailState.value = email
    }

    fun setPassword(password: String) {
        passwordState.value = password
    }

    fun setCheckboxChecked(isChecked: Boolean) {
        checkboxState.value = isChecked
    }

    private fun validateFields(
        email: String,
        password: String,
        isChecked: Boolean
    ): FormState {
        val emailError = if (email.isEmpty() || !isValidEmail(email)) {
            R.string.email_error
        } else {
            null
        }

        val passwordError = if (password.length < 6 || password.length > 18) {
            R.string.password_error
        } else {
            null
        }

        val isButtonEnabled = emailError == null && passwordError == null && isChecked

        return FormState(emailError, passwordError, isChecked, isButtonEnabled)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length in 6..18 &&
                (password.matches(Regex(".*[a-z].*")) || password.matches(Regex(".*[A-Z].*"))) &&
                password.matches(Regex(".*\\d.*")) &&
                password.matches(Regex(".*[!@#$%^&*()-=_+|;':\",.<>/?].*"))
    }
}