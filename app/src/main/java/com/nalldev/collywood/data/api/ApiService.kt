package com.nalldev.collywood.data.api

import com.nalldev.collywood.BuildConfig.API_KEY
import com.nalldev.collywood.domain.model.MovieModel
import com.nalldev.collywood.domain.model.SeriesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "id-Indonesian",
        @Query("page") page: Int = 1
    ): MovieModel

    @GET("movie/top_rated")
    suspend fun getDigital(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "id-Indonesian",
        @Query("page") page: Int = 1
    ): MovieModel
    @GET("tv/airing_today")
    suspend fun getAiringToday(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "id-Indonesian",
        @Query("page") page: Int = 1
    ): SeriesModel

    @GET("tv/popular")
    suspend fun getSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "id-Indonesian",
        @Query("page") page: Int = 1
    ): SeriesModel
}