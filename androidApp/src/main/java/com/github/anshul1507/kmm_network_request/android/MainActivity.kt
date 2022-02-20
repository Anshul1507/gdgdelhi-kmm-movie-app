package com.github.anshul1507.kmm_network_request.android

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.anshul1507.kmm_network_request.Greeting
import com.github.anshul1507.kmm_network_request.repo.ModelRepo
import kotlinx.coroutines.runBlocking

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

//        viewModel.getModelData().observe(this) {
//            if (it.isEmpty())
//                return@observe
//
//            val list = it
//            Log.e("test", "result: " + list.toString())
//        }
        runBlocking {
            val response = ModelRepo().getPopularMoviesData()
            Log.e("test", "result: " + response.moviesList.count())
        }
    }
}
