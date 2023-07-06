package com.luu.android.ext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.luu.android.simpeldesa.R
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.drop

fun View.gone(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.VISIBLE
}

val EditText.flow: Flow<String>
    get() {
        val flow = MutableStateFlow("")
        doOnTextChanged { text, _, _, _ ->
            flow.value = text.toString()
        }
        return flow.drop(1)
    }

fun Context.dpToPx(dp: Float): Int {
    val displayMetrics = resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).toInt()
}

fun View.showHidePassword(et: EditText) {
    var state = true
    setOnClickListener {
        if (state) {
            et.transformationMethod = HideReturnsTransformationMethod.getInstance()
            state = false
        } else {
            et.transformationMethod = PasswordTransformationMethod.getInstance()
            state = true
        }
        et.setSelection(et.text.length)
    }
}

fun Int?.nullToZero() = this ?: 0