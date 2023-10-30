package com.nalldev.collywood.domain.repository

import com.nalldev.collywood.domain.model.MovieModel
import com.nalldev.collywood.domain.model.SeriesModel

interface MoviesRepository {
    suspend fun fetchMovies() : MovieModel
    suspend fun fetchDigital() : MovieModel
    suspend fun fetchAiringToday() : SeriesModel
    suspend fun fetchSeries() : SeriesModel
}