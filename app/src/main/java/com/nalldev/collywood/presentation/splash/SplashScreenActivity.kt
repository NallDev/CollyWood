package com.nalldev.collywood.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.nalldev.collywood.BaseActivity
import com.nalldev.collywood.databinding.ActivitySplashScreenBinding
import com.nalldev.collywood.presentation.onboarding.OnBoardingActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun getViewBinding() = ActivitySplashScreenBinding.inflate(layoutInflater)

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityScope.launch {
            delay(2000)
            Intent(this@SplashScreenActivity, OnBoardingActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    override fun onDestroy() {
        activityScope.cancel()
        super.onDestroy()
    }
}