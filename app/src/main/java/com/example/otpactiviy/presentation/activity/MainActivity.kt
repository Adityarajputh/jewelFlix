package com.example.otpactiviy.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.otpactiviy.R
import com.example.otpactiviy.presentation.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}