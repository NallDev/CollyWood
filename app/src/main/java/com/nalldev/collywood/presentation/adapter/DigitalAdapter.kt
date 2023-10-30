package com.nalldev.collywood.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nalldev.collywood.databinding.ItemDigitalBinding
import com.nalldev.collywood.domain.model.ResultsMovie
import com.nalldev.collywood.presentation.util.Component
import com.nalldev.collywood.presentation.util.DataMovies

class DigitalAdapter : RecyclerView.Adapter<DigitalAdapter.ViewHolder>() {
    private lateinit var contextAdapter : Context

    private val diffCallback = object : DiffUtil.ItemCallback<ResultsMovie>() {
        override fun areItemsTheSame(oldItem: ResultsMovie, newItem: ResultsMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultsMovie, newItem: ResultsMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<ResultsMovie>) = differ.submitList(list)

    inner class ViewHolder(val binding: ItemDigitalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDigitalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

                tvTitle.text = item.title
                tvYear.text = Component.changeDateToYear(item.releaseDate)

                if (position == itemCount - 1) {
                    val params = binding.root.layoutParams as ViewGroup.MarginLayoutParams
                    params.marginEnd = 40
                    binding.root.layoutParams = params
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}