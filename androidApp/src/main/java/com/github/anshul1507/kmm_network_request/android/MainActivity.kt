package com.github.anshul1507.kmm_network_request.android

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPopularMovies().observe(this) {
            if (it.isEmpty())
                return@observe

            val list = it
            Log.e("test", "result: " + list.size)
        }
    }
}
