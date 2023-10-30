package com.nalldev.collywood.di

import com.nalldev.collywood.presentation.logres.LogResViewModel
import com.nalldev.collywood.presentation.movies.MoviesViewModel
import com.nalldev.collywood.presentation.movies.ProfileViewModel
import com.nalldev.collywood.presentation.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LogResViewModel(get(), get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { OnBoardingViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}