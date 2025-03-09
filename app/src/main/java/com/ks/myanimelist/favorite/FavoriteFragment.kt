package com.ks.myanimelist.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ks.myanimelist.R
import com.ks.myanimelist.core.ui.AnimeAdapter
import com.ks.myanimelist.core.ui.ViewModelFactory
import com.ks.myanimelist.databinding.FragmentFavoriteBinding
import com.ks.myanimelist.detail.DetailAnimeActivity
import com.ks.myanimelist.home.HomeViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val animeAdapter = AnimeAdapter()
            animeAdapter.onItemClick = { selectedData ->
                val intent = Intent(requireActivity(), DetailAnimeActivity::class.java)
                intent.putExtra(DetailAnimeActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.anime.observe(viewLifecycleOwner) { anime ->
                animeAdapter.submitList(anime)
                binding.viewEmpty.root.visibility = if (anime.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvAnime) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = animeAdapter
            }
        }
    }
}