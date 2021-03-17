package com.example.mmy.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.mmy.androidApp.databinding.ActivityMainBinding
import com.example.mmy.shared.api.ApiEngine
import com.example.mmy.shared.model.Profile
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val apiEngine = ApiEngine()
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferenceManager: PreferenceManager
    private val mainScope = MainScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        preferenceManager = PreferenceManager(this)
        title = getString(R.string.profile)
        setContentView(binding.root)
        fetchProfile()
        binding.buttonSubmit.setOnClickListener { callUpdateProfile() }
    }

    private fun fetchProfile() {
        showLoading()
        mainScope.launch {
            var profile: Profile? = null
            kotlin.runCatching {
                profile = apiEngine.getProfile(preferenceManager.getUserID() ?: "")
            }.onSuccess {
//                withContext(Dispatchers.Main) {
                    hideLoading()
                    binding.apply {
                        textViewId.text = profile?.id
                        editTextFirstName.setText(profile?.firstName)
                        editTextLastName.setText(profile?.lastName)
                        editTextPhone.setText(profile?.phoneNumber)
                        editTextAddress.setText(profile?.address)
                    }
//                }
            }.onFailure {
//                withContext(Dispatchers.IO) {
                hideLoading()
                    showError(it.message ?: getString(R.string.error_unknown))
//                }
            }
        }
    }

    private fun callUpdateProfile() {
        val profile = Profile(binding.textViewId.text.toString(),
            binding.editTextFirstName.text.toString(),
            binding.editTextLastName.text.toString(),
            binding.editTextPhone.text.toString(),
            binding.editTextAddress.text.toString()
        )
        showLoading()
        mainScope.launch {
            kotlin.runCatching {
                apiEngine.updateProfile(profile)
            }.onSuccess {
                hideLoading()
                Snackbar.make(binding.root, R.string.success_update, Snackbar.LENGTH_SHORT).show()
            }.onFailure {
                hideLoading()
                showError(getString(R.string.error_update))
            }
        }
    }

    private fun showLoading() {
        binding.loader.visibility = View.VISIBLE
        binding.content.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.content.visibility = View.VISIBLE
        binding.loader.visibility = View.GONE
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this@MainActivity).setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}
