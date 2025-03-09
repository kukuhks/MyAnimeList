package com.ks.myanimelist.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.ks.myanimelist.R
import com.ks.myanimelist.core.data.source.Resource
import com.ks.myanimelist.core.ui.AnimeAdapter
import com.ks.myanimelist.core.ui.ViewModelFactory
import com.ks.myanimelist.databinding.FragmentHomeBinding
import com.ks.myanimelist.detail.DetailAnimeActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.anime.observe(viewLifecycleOwner) { anime ->
                if(anime != null) {
                    when(anime) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.GONE
                            animeAdapter.submitList(anime.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = anime.message ?: getString(R.string.error)
                        }
                    }
                }
            }

            with(binding.rvAnime) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = animeAdapter
            }
        }
    }
}