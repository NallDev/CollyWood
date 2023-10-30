package com.nalldev.collywood.di

import com.nalldev.collywood.data.repository.MoviesRepositoryImpl
import com.nalldev.collywood.data.repository.SessionRepositoryImpl
import com.nalldev.collywood.data.repository.UserRepositoryImpl
import com.nalldev.collywood.domain.repository.MoviesRepository
import com.nalldev.collywood.domain.repository.SessionRepository
import com.nalldev.collywood.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { (UserRepositoryImpl(get())) }
    factory<MoviesRepository> { (MoviesRepositoryImpl(get())) }
    factory<SessionRepository> { (SessionRepositoryImpl(get())) }
}