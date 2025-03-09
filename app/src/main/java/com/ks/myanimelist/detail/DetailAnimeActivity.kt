package com.ks.myanimelist.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.ks.myanimelist.R
import com.ks.myanimelist.core.data.source.local.entity.AnimeEntity
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.core.ui.ViewModelFactory
import com.ks.myanimelist.databinding.ActivityDetailAnimeBinding

class DetailAnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAnimeBinding
    private lateinit var detailAnimeViewModel: DetailAnimeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val factory = ViewModelFactory.getInstance(this)
        detailAnimeViewModel = ViewModelProvider(this, factory)[DetailAnimeViewModel::class.java]

        val detailAnime = getParcelableExtra(intent, EXTRA_DATA, Anime::class.java)
        showDetailAnime(detailAnime)

        val collapsingToolbar = binding.detailAppBar.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.title = detailAnime?.title

    }

    @SuppressLint("SetTextI18n")
    private fun showDetailAnime(detailAnime: Anime?) {
        detailAnime?.let {
            supportActionBar?.title = detailAnime.title
            Log.d("DetailAnimeActivity", "Anime Title: ${detailAnime.title}")
            Glide.with(this@DetailAnimeActivity)
                .load(detailAnime.imageUrl)
                .into(binding.ivDetailImage)
            binding.contentDetailAnime.tvScore.text = detailAnime.score.toString()
            binding.contentDetailAnime.tvStudio.text = detailAnime.studio
            binding.contentDetailAnime.tvAired.text = detailAnime.aired
            binding.contentDetailAnime.tvType.text = detailAnime.type
            binding.contentDetailAnime.tvContentSynopsis.text = detailAnime.synopsis
            Log.d("DetailAnime", "Name Studio: ${detailAnime.studio}")
            Log.d("DetailAnime", "Date Aired: ${detailAnime.aired}")

            var statusFavorite = detailAnime.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailAnimeViewModel.setFavoriteAnime(detailAnime, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}