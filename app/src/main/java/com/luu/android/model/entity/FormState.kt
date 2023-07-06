package com.luu.android.model.entity

data class FormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isCheckboxChecked: Boolean = false,
    val isButtonEnabled: Boolean = false
)