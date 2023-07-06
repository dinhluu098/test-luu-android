package com.luu.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.luu.android.Constant.FAIR
import com.luu.android.Constant.GOOD
import com.luu.android.Constant.STRONG
import com.luu.android.Constant.TOO_SHORT
import com.luu.android.Constant.WEAK
import com.luu.android.simpeldesa.R
import com.luu.android.simpeldesa.databinding.LayoutPasswordLevelProgressBarBinding
import com.luu.android.ui.login.LoginActivity
import com.luu.android.ui.login.LoginViewModel

class PasswordLevelProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewBinding =
        LayoutPasswordLevelProgressBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.PasswordLevelProgressBar, 0, 0)
            .also { typeArray ->
                try {
                    with(viewBinding) {
                    }
                } finally {
                    typeArray.recycle()
                }
            }
    }

    fun setLevelPassword(passwordLevel: LoginActivity.PasswordLevel) {
        with(viewBinding) {
            when (passwordLevel) {
                LoginActivity.PasswordLevel.TooShort -> {
                    pbPasswordLevel.progress = 0
                    pbPasswordLevel.progressDrawable =
                        ContextCompat.getDrawable(context, R.drawable.bg_progress_bar)
                    tvPasswordLevel.text = "Too short"
                    tvPasswordLevel.setTextColor(ContextCompat.getColor(context, R.color.white_50))
                }

                LoginActivity.PasswordLevel.Weak -> {
                    pbPasswordLevel.progress = 25
                    pbPasswordLevel.progressDrawable =
                        ContextCompat.getDrawable(context, R.drawable.bg_progress_bar_week)
                    tvPasswordLevel.text = "Weak"
                    tvPasswordLevel.setTextColor(ContextCompat.getColor(context, R.color.C_E05151))
                }

                LoginActivity.PasswordLevel.Fair -> {
                    pbPasswordLevel.progress = 50
                    pbPasswordLevel.progressDrawable =
                        ContextCompat.getDrawable(context, R.drawable.bg_progress_bar_fair)
                    tvPasswordLevel.text = "Fair"
                    tvPasswordLevel.setTextColor(ContextCompat.getColor(context, R.color.C_E3A063))
                }

                LoginActivity.PasswordLevel.Good -> {
                    pbPasswordLevel.progress = 75
                    pbPasswordLevel.progressDrawable =
                        ContextCompat.getDrawable(context, R.drawable.bg_progress_bar_good)
                    tvPasswordLevel.text = "Good"
                    tvPasswordLevel.setTextColor(ContextCompat.getColor(context, R.color.C_647FFF))
                }

                LoginActivity.PasswordLevel.Strong -> {
                    pbPasswordLevel.progress = 100
                    pbPasswordLevel.progressDrawable =
                        ContextCompat.getDrawable(context, R.drawable.bg_progress_bar_strong)
                    tvPasswordLevel.text = "Strong"
                    tvPasswordLevel.setTextColor(ContextCompat.getColor(context, R.color.C_91E2B7))
                }
            }
        }
    }
}