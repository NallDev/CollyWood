package com.nalldev.collywood.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nalldev.collywood.databinding.ItemSeriesBinding
import com.nalldev.collywood.domain.listener.OnItemClickListener
import com.nalldev.collywood.domain.model.ResultsSeries
import com.nalldev.collywood.presentation.util.Component.itemToModel
import com.nalldev.collywood.presentation.util.DataMovies

class SeriesAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {
    private lateinit var contextAdapter : Context

    private val diffCallback = object : DiffUtil.ItemCallback<ResultsSeries>() {
        override fun areItemsTheSame(oldItem: ResultsSeries, newItem: ResultsSeries): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultsSeries, newItem: ResultsSeries): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ResultsSeries>) = differ.submitList(list)

    inner class ViewHolder(val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        contextAdapter = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        with(holder) {
            binding.apply {
                Glide.with(contextAdapter)
                    .load("https://image.tmdb.org/t/p/w600_and_h600_bestv2${item.posterPath}")
                    .into(ivContent)

                val data = item.genreIds
                val dataGenres = DataMovies.genreList

                val listGenres = data.mapNotNull { genreId ->
                    dataGenres.find { it.first == genreId }?.second
                }

                if (listGenres.isNotEmpty()) {
                    val genres = listGenres.joinToString(", ")
                    tvGenres.text = genres
                }

                tvTitle.text = item.name

                if (position == itemCount - 1) {
                    val params = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                    params.marginEnd = 40
                    binding.root.layoutParams = params
                }

                root.setOnClickListener {
                    onItemClickListener.onItemClick(itemToModel(item))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}