package com.ks.myanimelist.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.ks.myanimelist.R
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.databinding.ActivityDetailAnimeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAnimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAnimeBinding
    private val detailAnimeViewModel: DetailAnimeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAnimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailAnime = getParcelableExtra(intent, EXTRA_DATA, Anime::class.java)
        showDetailAnime(detailAnime)

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.text = detailAnime?.title

    }

    @SuppressLint("SetTextI18n")
    private fun showDetailAnime(detailAnime: Anime?) {
        detailAnime?.let {
            Glide.with(this@DetailAnimeActivity)
                .load(detailAnime.imageUrl)
                .into(binding.ivDetailImage)
            binding.tvScore.text = "⭐ " + detailAnime.score.toString()
            binding.tvEpisodes.text = detailAnime.episodes.toString() + " Episodes"
            binding.tvYear.text = detailAnime.year.toString()
            binding.tvType.text = detailAnime.type
            binding.tvContentSynopsis.text = detailAnime.synopsis

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