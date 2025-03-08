package com.ks.myanimelist.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.ks.myanimelist.R
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
//        showDetailAnime(detailAnime)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}