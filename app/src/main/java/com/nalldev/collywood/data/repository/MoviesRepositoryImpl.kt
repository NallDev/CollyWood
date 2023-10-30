package com.nalldev.collywood.data.repository

import com.nalldev.collywood.data.api.ApiService
import com.nalldev.collywood.domain.repository.MoviesRepository

class MoviesRepositoryImpl(private val client : ApiService) : MoviesRepository {
    override suspend fun fetchMovies() = client.getMovies()
    override suspend fun fetchDigital() = client.getDigital()
    override suspend fun fetchAiringToday() = client.getAiringToday()
    override suspend fun fetchSeries() = client.getSeries()
}