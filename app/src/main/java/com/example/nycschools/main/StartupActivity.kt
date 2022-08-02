package com.example.nycschools.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nycschools.databinding.ActivitySplashScreenBinding

/**
 * An start up full-screen activity that is just a splash screen for startup purposes.
 */
class StartupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.postDelayed({
            SchoolsListActivity.launch(this)
            finish()
        }, 3000)

    }
}