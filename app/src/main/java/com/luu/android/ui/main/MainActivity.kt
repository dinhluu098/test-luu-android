package com.luu.android.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luu.android.simpeldesa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
    }
}