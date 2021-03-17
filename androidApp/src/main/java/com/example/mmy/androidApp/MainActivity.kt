package com.example.mmy.androidApp

import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.mmy.shared.Greeting
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.mmy.androidApp.databinding.ActivityMainBinding
import com.example.mmy.shared.api.ApiEngine
import com.example.mmy.shared.model.Profile
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val apiEngine = ApiEngine()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        title = getString(R.string.profile)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val profile = apiEngine.getProfile("")
            withContext(Dispatchers.Main) {
                binding.apply {
                    textViewId.text = profile.id
                    editTextFirstName.setText(profile.firstName)
                    editTextLastName.setText(profile.lastName)
                    editTextPhone.setText(profile.phoneNumber)
                    editTextAddress.setText(profile.address)
                }
            }
        }
        binding.buttonSubmit.setOnClickListener {
            val profile = Profile(binding.textViewId.text.toString(),
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextAddress.text.toString()
            )
            lifecycleScope.launch {
                try {
                    apiEngine.updateProfile(profile)
                } catch (throwable: Throwable) {
                    withContext(Dispatchers.Main) {
                        AlertDialog.Builder(this@MainActivity).setMessage(R.string.error_update)
                            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                            .create()
                            .show()
                    }
                }

                withContext(Dispatchers.Main) {
                    Snackbar.make(binding.root, R.string.success_update, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun showLoading() {

    }
}
