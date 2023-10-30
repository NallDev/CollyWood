package com.nalldev.collywood.presentation.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nalldev.collywood.R
import com.nalldev.collywood.domain.model.ResultsSeries
import com.nalldev.collywood.presentation.movies.screens.CaraouselFragment
import com.nalldev.collywood.presentation.onboarding.screens.FirstOnboardingFragment

class CaraouselPagerAdapter(fragment : FragmentActivity, private val data : List<ResultsSeries>) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CaraouselFragment.newInstance(
                param1 = data[0]
            )
            1 -> CaraouselFragment.newInstance(
                param1 = data[1]
            )
            2 -> CaraouselFragment.newInstance(
                param1 = data[2]
            )
            3 -> CaraouselFragment.newInstance(
                param1 = data[3]
            )
            else -> CaraouselFragment.newInstance(
                param1 = data[4]
            )
        }
    }
}