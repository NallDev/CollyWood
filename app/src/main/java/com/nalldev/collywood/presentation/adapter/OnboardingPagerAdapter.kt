package com.nalldev.collywood.presentation.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nalldev.collywood.R
import com.nalldev.collywood.presentation.onboarding.screens.FirstOnboardingFragment

class OnboardingPagerAdapter(fragment : FragmentActivity, private val context: Context) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FirstOnboardingFragment.newInstance(
                param1 = context.resources.getString(R.string.onboarding_title_1),
                param2 = context.resources.getString(R.string.onboarding_subtitle_1),
                param3 = R.drawable.first_onboarding
            )
            1 -> FirstOnboardingFragment.newInstance(
                param1 = context.resources.getString(R.string.onboarding_title_2),
                param2 = context.resources.getString(R.string.onboarding_subtitle_2),
                param3 = R.drawable.second_onboarding
            )
            else -> FirstOnboardingFragment.newInstance(
                param1 = context.resources.getString(R.string.onboarding_title_3),
                param2 = context.resources.getString(R.string.onboarding_subtitle_3),
                param3 = R.drawable.third_onboarding
            )
        }
    }
}