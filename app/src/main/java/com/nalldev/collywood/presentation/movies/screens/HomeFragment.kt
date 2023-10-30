package com.nalldev.collywood.presentation.movies.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.FragmentHomeBinding
import com.nalldev.collywood.presentation.adapter.CaraouselPagerAdapter
import com.nalldev.collywood.presentation.adapter.DigitalAdapter
import com.nalldev.collywood.presentation.adapter.MoviesAdapter
import com.nalldev.collywood.presentation.adapter.SeriesAdapter
import com.nalldev.collywood.presentation.movies.MoviesViewModel
import com.nalldev.collywood.presentation.util.RequestState
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val moviesAdapter by lazy {
        MoviesAdapter()
    }

    private val digitalAdapter by lazy {
        DigitalAdapter()
    }

    private val seriesAdapter by lazy {
        SeriesAdapter()
    }

    private lateinit var caraouselPager : CaraouselPagerAdapter

    private val moviesViewModel : MoviesViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnce()
        initObserver()
    }

    private fun initObserver() {
        moviesViewModel.fetchMovies().observe(viewLifecycleOwner) { state ->
            when(state) {
                is RequestState.Success -> {
                    binding.apply {
                        shimmerMovies.isInvisible = true
                        moviesAdapter.submitList(state.data.results)
                    }
                }
                is RequestState.Loading -> {
                    binding.shimmerMovies.isInvisible = false
                }
                is RequestState.Error -> {
                    context?.let {
                        Toast.makeText(it, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        moviesViewModel.fetchDigital().observe(viewLifecycleOwner) { state ->
            when(state) {
                is RequestState.Success -> {
                    binding.apply {
                        shimmerDigital.isInvisible = true
                        digitalAdapter.submitList(state.data.results)
                    }
                }
                is RequestState.Loading -> {
                    binding.shimmerDigital.isInvisible = false
                }
                is RequestState.Error -> {
                    context?.let {
                        Toast.makeText(it, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        moviesViewModel.fetchAiringToday().observe(viewLifecycleOwner) { state ->
            when(state) {
                is RequestState.Success -> {
                    binding.apply {
                        shimmerVp.isInvisible = true
                        activity?.let {
                            caraouselPager = CaraouselPagerAdapter(it, state.data.results)
                            vpContent.adapter = caraouselPager
                            indicatorContent.apply {
                                activity?.let { activity ->
                                    setSliderColor(activity.getColor(R.color.white), activity.getColor(R.color.yellow))
                                }
                                setSliderWidth(resources.getDimension(com.intuit.sdp.R.dimen._8sdp))
                                setSliderHeight(resources.getDimension(com.intuit.sdp.R.dimen._8sdp))
                                setSlideMode(IndicatorSlideMode.SMOOTH)
                                setIndicatorStyle(IndicatorStyle.CIRCLE)
                                setupWithViewPager(vpContent)
                            }
                        }
                    }
                }
                is RequestState.Loading -> {
                    binding.shimmerVp.isInvisible = false
                }
                is RequestState.Error -> {
                    context?.let {
                        Toast.makeText(it, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        moviesViewModel.fetchSeries().observe(viewLifecycleOwner) { state ->
            when(state) {
                is RequestState.Success -> {
                    binding.apply {
                        shimmerSeries.isInvisible = true
                        seriesAdapter.submitList(state.data.results)
                        activity?.let {
                            caraouselPager = CaraouselPagerAdapter(it, state.data.results)
                            vpContent.adapter = caraouselPager
                        }
                    }
                }
                is RequestState.Loading -> {
                    binding.shimmerSeries.isInvisible = false
                }
                is RequestState.Error -> {
                    context?.let {
                        Toast.makeText(it, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initOnce() {
        binding.apply {
            rvMovies.adapter = moviesAdapter
            rvDigital.adapter = digitalAdapter
            rvSeries.adapter = seriesAdapter
        }
    }
}