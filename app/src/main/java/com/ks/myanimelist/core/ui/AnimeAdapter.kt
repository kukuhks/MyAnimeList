package com.ks.myanimelist.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ks.myanimelist.core.domain.model.Anime
import com.ks.myanimelist.databinding.ItemListAnimeBinding

class AnimeAdapter : ListAdapter<Anime, AnimeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Anime) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListAnimeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Anime) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.title
//            binding.tvItemSubtitle.text = data.rating
        }
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Anime> =
            object : DiffUtil.ItemCallback<Anime>() {
                override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                    return oldItem.malId == newItem.malId
                }

                override fun areContentsTheSame(
                    oldItem: Anime,
                    newItem: Anime
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}