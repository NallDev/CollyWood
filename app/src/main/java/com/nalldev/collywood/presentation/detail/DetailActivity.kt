package com.nalldev.collywood.presentation.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.nalldev.collywood.BaseActivity
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.ActivityDetailBinding
import com.nalldev.collywood.domain.model.DetailModel
import eightbitlab.com.blurview.RenderScriptBlur

class DetailActivity : BaseActivity<ActivityDetailBinding>() {
    override fun getViewBinding() = ActivityDetailBinding.inflate(layoutInflater)

    private var data : DetailModel? = DetailModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent.getParcelableExtra(PARAMS)

        initOnce()
        initEventListener()
    }

    private fun initEventListener() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initOnce() {
        binding.apply {
            bvBack.setupWith(binding.root, RenderScriptBlur(this@DetailActivity))
                .setBlurRadius(25f)
            bvPlay.setupWith(binding.root, RenderScriptBlur(this@DetailActivity))
                .setBlurRadius(25f)

            data?.let { data ->
                Glide.with(this@DetailActivity)
                    .load(data.posterPath)
                    .into(ivContent)

                tvTitle.text = data.title
                tvRate.text = data.vote
                tvDate.text = data.date
                tvSynopsis.text = data.overview

                for (genre in data.genre) {
                    val chip = Chip(this@DetailActivity)
                    chip.text = genre
                    chip.setTextColor(getColor(R.color.white))
                    chip.setChipBackgroundColorResource(R.color.purple)
                    cgGenre.addView(chip)
                }
            }
        }
    }

    companion object {
        const val PARAMS = "params"
    }
}