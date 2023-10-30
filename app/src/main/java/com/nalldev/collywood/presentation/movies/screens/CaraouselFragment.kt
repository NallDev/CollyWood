package com.nalldev.collywood.presentation.movies.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.FragmentCaraouselBinding
import com.nalldev.collywood.domain.model.ResultsSeries

class CaraouselFragment : Fragment() {
    private var _binding: FragmentCaraouselBinding? = null
    private val binding get() = _binding!!

    private lateinit var data: ResultsSeries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_PARAM1)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCaraouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            context?.let {
                Glide.with(it)
                    .load("https://image.tmdb.org/t/p/w600_and_h600_bestv2${data.posterPath}")
                    .into(ivContent)
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: ResultsSeries) =
            CaraouselFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }
}