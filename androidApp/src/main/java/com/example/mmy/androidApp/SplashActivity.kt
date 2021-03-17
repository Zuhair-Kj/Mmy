package com.example.mmy.androidApp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {
    lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager = PreferenceManager(this)
        setContentView(R.layout.activity_splash)
        goToNextScreen()
    }

    private fun goToNextScreen() {
        if (preferenceManager.getUserID().isNullOrEmpty())
            startActivity(Intent(this, LoginActivity::class.java))
        else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}