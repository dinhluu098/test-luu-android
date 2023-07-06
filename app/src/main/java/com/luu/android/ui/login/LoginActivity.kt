package com.luu.android.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.luu.android.Constant.MIN_CHAR_PASSWORD
import com.luu.android.ext.flow
import com.luu.android.ext.gone
import com.luu.android.ext.showHidePassword
import com.luu.android.model.request.SigninRequest
import com.luu.android.model.request.SignupRequest
import com.luu.android.simpeldesa.databinding.ActivityLoginBinding
import com.luu.android.ui.category.CategoryActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        initView()
        onSubscribe()
    }

    private fun initView() {
        with(viewBinding) {
            edtEmail.doAfterTextChanged {
                tvEmail.gone(isGone = it.isNullOrEmpty())
                loginViewModel.setEmail(it.toString())
            }
            edtPassword.doAfterTextChanged {
                tvPassword.gone(isGone = it.isNullOrEmpty())
                loginViewModel.setPassword(it.toString())
            }
            lifecycleScope.launch {
                isValidPassword(edtPassword.flow)
                    .collect { passwordLevel ->
                        plpbPassword.visibility = View.VISIBLE
                        plpbPassword.setLevelPassword(passwordLevel)
                    }
            }
            cbYearOld.setOnCheckedChangeListener { _, isChecked ->
                loginViewModel.setCheckboxChecked(isChecked)
            }
            ivNext.setOnClickListener {
                loginViewModel.signup(
                    SignupRequest(
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString()
                    )
                )
            }
            ivShowPassword.showHidePassword(edtPassword)
            lifecycleScope.launch {
                loginViewModel.formState.collect { formState ->
                    ivNext.isEnabled = formState.isButtonEnabled
                }
            }
        }
    }

    private fun onSubscribe() {
        with(loginViewModel) {
            signupSuccess.observe(this@LoginActivity) {
                signin(
                    SigninRequest(
                        email = viewBinding.edtEmail.text.toString(),
                        password = viewBinding.edtPassword.text.toString()
                    )
                )
            }
            signinSuccess.observe(this@LoginActivity) {
                startActivity(Intent(this@LoginActivity, CategoryActivity::class.java))
            }
        }
    }

    sealed class PasswordLevel {
        object Weak : PasswordLevel()
        object Fair : PasswordLevel()
        object Good : PasswordLevel()
        object Strong : PasswordLevel()
        object TooShort : PasswordLevel()
    }

    private fun isValidPassword(password: Flow<String>): Flow<PasswordLevel> {
        val lowerAndUpperCaseRegex = Regex("(?=.*[a-z])(?=.*[A-Z]).+")
        val numericRegex = Regex(".*\\d.*")
        val specialCharsRegex = Regex(".*[!@#\$%^&*()_+].*")

        val defaultLevel = MutableStateFlow(PasswordLevel.Weak)

        return password.map { input ->
            if (input.length < MIN_CHAR_PASSWORD) {
                PasswordLevel.TooShort
            } else {
                when {
                    input.matches(lowerAndUpperCaseRegex) &&
                            input.matches(numericRegex) && input.matches(specialCharsRegex) -> PasswordLevel.Strong

                    (input.matches(lowerAndUpperCaseRegex) && input.matches(numericRegex)) ||
                            (input.matches(lowerAndUpperCaseRegex) && input.matches(
                                specialCharsRegex
                            )) ||
                            (input.matches(numericRegex) && input.matches(specialCharsRegex)) -> PasswordLevel.Good

                    else -> PasswordLevel.Fair
                }
            }
        }.combine(defaultLevel) { level, _ ->
            level
        }
    }
}

