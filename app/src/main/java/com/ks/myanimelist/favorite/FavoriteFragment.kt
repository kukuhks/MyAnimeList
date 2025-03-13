package com.ks.myanimelist.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ks.myanimelist.core.ui.AnimeAdapter
import com.ks.myanimelist.databinding.FragmentFavoriteBinding
import com.ks.myanimelist.detail.DetailAnimeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
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