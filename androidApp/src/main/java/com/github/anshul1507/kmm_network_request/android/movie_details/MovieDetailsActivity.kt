package com.github.anshul1507.kmm_network_request.android.movie_details

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.github.anshul1507.kmm_network_request.android.R
import com.github.anshul1507.kmm_network_request.android.databinding.ActivityMovieDetailsBinding
import com.github.anshul1507.kmm_network_request.model.MovieDetails
import com.github.anshul1507.kmm_network_request.repo.posterBaseUrl
import java.text.NumberFormat
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var mContext: Context
    private lateinit var titleMovie: String
    private lateinit var movieHomePageUrl: String
    private var customTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient? = null
    var customTabsSession: CustomTabsSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mContext = applicationContext
        val movieId: Int = intent.getIntExtra("movieID", 1)

        viewModel.getMovieDetails(movieId).observe(this) {
            if (it == null)
                return@observe

            val details = it
            Log.e("test", "details: " + details)

            binding.progressBar.visibility = View.GONE
            binding.parent.visibility = View.VISIBLE

            fillUI(it)
            titleMovie = it.title
            movieHomePageUrl = it.homepage

        }

        binding.fabTrailer.setOnClickListener {
            val ytTitle = "http://www.youtube.com/results?search_query=${titleMovie}"
            ytParser(ytTitle)
        }

        binding.fabHomepage.setOnClickListener {
            customTabLinking(movieHomePageUrl)
        }
    }

    private fun fillUI(it: MovieDetails) {
        binding.movieTitle.text = it.title
        binding.movieTagline.text = it.tagline
        binding.movieReleaseDate.text = it.releaseDate
        binding.movieRating.text = it.rating.toString()
        binding.movieRuntime.text = it.runtime.toString() + " mins"
        binding.movieOverview.text = Html.fromHtml(it.overview)

        val formatCur = NumberFormat.getCurrencyInstance(Locale.US)
        binding.movieBudget.text = formatCur.format(it.budget)
        binding.movieRevenue.text = formatCur.format(it.revenue)

        val moviePosterUrl = posterBaseUrl + it.posterPath
        Glide.with(this).load(moviePosterUrl).into(binding.imagePoster)
    }

    private fun ytParser(id: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(id)
        startActivity(intent)
    }

    fun customTabLinking(url: String?) {
        customTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(
                componentName: ComponentName,
                customTabsClient: CustomTabsClient
            ) { //pre-warning means to fast the surfing
                mClient = customTabsClient
                mClient!!.warmup(0L)
                customTabsSession = mClient!!.newSession(null)
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
                mClient = null
            }
        }
        CustomTabsClient.bindCustomTabsService(
            mContext,
            "com.android.chrome",
            customTabsServiceConnection as CustomTabsServiceConnection
        )
        val uri = Uri.parse(url)
        //Create an Intent Builder
        val intentBuilder = CustomTabsIntent.Builder()
        intentBuilder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimaryDark))
        intentBuilder.setSecondaryToolbarColor(
            ContextCompat.getColor(
                mContext,
                R.color.colorPrimaryDark
            )
        )
        //Set Start and Exit Animations
        intentBuilder.setStartAnimations(mContext, R.anim.slide_in_right, R.anim.slide_out_left)
        intentBuilder.setExitAnimations(
            mContext,
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
        //build custom tabs intent
        val customTabsIntent = intentBuilder.build()
        customTabsIntent.intent.setPackage("com.android.chrome")
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intentBuilder.setShowTitle(true)
        intentBuilder.enableUrlBarHiding()
        customTabsIntent.launchUrl(mContext, uri)
    }
}