package com.nalldev.collywood.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import com.nalldev.collywood.BaseActivity
import com.nalldev.collywood.R
import com.nalldev.collywood.databinding.ActivityOnBoardingBinding
import com.nalldev.collywood.presentation.adapter.OnboardingPagerAdapter
import com.nalldev.collywood.presentation.logres.LoginRegisterActivity
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    override fun getViewBinding() = ActivityOnBoardingBinding.inflate(layoutInflater)

    private val onboardingPagerAdapter by lazy {
        OnboardingPagerAdapter(this, this)
    }

    private val onBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
        initOnce()
        initEventListener()
    }

    private fun initObserver() {
        onBoardingViewModel.getOnBoardingSaw().observe(this) { wasSaw ->
            if (wasSaw) {
                Intent(this@OnBoardingActivity, LoginRegisterActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun initOnce() {
        binding.apply {
            vpContent.adapter = onboardingPagerAdapter
            indicatorContent.apply {
                setSliderColor(getColor(R.color.white), getColor(R.color.black))
                setSliderWidth(resources.getDimension(com.intuit.sdp.R.dimen._24sdp))
                setSliderHeight(resources.getDimension(com.intuit.sdp.R.dimen._8sdp))
                setSlideMode(IndicatorSlideMode.WORM)
                setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                setupWithViewPager(vpContent)
            }
        }
    }

    private fun initEventListener() {
        binding.apply {
            btnNext.setOnClickListener {
                if (vpContent.currentItem > vpContent.childCount) {
                    onBoardingViewModel.onBoardingSaw()
                    Intent(this@OnBoardingActivity, LoginRegisterActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                } else {
                    vpContent.setCurrentItem(vpContent.currentItem + 1, true)
                }
            }

            btnToLogin.setOnClickListener {
                onBoardingViewModel.onBoardingSaw()
                Intent(this@OnBoardingActivity, LoginRegisterActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

}