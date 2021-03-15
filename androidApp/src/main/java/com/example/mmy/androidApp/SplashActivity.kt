package com.example.mmy.androidApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        goToNextScreen()
    }

    private fun goToNextScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}