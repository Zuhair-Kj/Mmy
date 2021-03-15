package com.example.mmy.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mmy.shared.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.mmy.shared.api.ApiEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val apiEngine = ApiEngine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.profile)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        lifecycleScope.launch(Dispatchers.IO) {
            val profile = apiEngine.getProfile("")
            withContext(Dispatchers.Main) {
                tv.text = profile.address
            }
        }

    }
}
