package com.example.mmy.androidApp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mmy.androidApp.databinding.ActivityLoginBinding
import com.example.mmy.shared.api.ApiEngine
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity: AppCompatActivity() {
    private val engine = ApiEngine()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceManager = PreferenceManager(this)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.buttonSubmit.setOnClickListener {
            try {
                login(
                    "",
                    ""
                )
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    private fun login(email: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val auth = engine.login(email, password)
            withContext(Dispatchers.Main) {
                if (auth.id.isNullOrEmpty()) {
                    Snackbar.make(binding.root, getString(R.string.error_wrong_email),Snackbar.LENGTH_SHORT).show()
                } else {
                    auth.id?.let {
                        preferenceManager.setUserID(it)
                        goToMain()
                    }
                }
            }
        }
    }
    private fun goToMain() {
        this@LoginActivity.apply {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }
}