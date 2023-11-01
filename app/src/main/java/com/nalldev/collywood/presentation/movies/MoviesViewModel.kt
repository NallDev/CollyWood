package com.nalldev.collywood.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nalldev.collywood.domain.model.MovieModel
import com.nalldev.collywood.domain.model.SeriesModel
import com.nalldev.collywood.domain.repository.MoviesRepository
import com.nalldev.collywood.presentation.util.RequestState
import okio.IOException
import retrofit2.HttpException

class MoviesViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun fetchMovies(): LiveData<RequestState<MovieModel>> = liveData {
        emit(RequestState.Loading)
        try {
            val response = moviesRepository.fetchMovies()
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "No internet connection"
                is HttpException -> {
                    when (e.code()) {
                        in 400..499 -> "Client error occurred"
                        in 500..599 -> "Server error occurred"
                        else -> "An error occurred"
                    }
                }
                else -> "An error occurred"
            }
            emit(RequestState.Error(errorMessage))
        }
    }

    fun fetchDigital(): LiveData<RequestState<MovieModel>> = liveData {
        emit(RequestState.Loading)
        try {
            val response = moviesRepository.fetchDigital()
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "No internet connection"
                is HttpException -> {
                    when (e.code()) {
                        in 400..499 -> "Client error occurred"
                        in 500..599 -> "Server error occurred"
                        else -> "An error occurred"
                    }
                }
                else -> "An error occurred"
            }
            emit(RequestState.Error(errorMessage))
        }
    }

    fun fetchAiringToday(): LiveData<RequestState<SeriesModel>> = liveData {
        emit(RequestState.Loading)
        try {
            val response = moviesRepository.fetchAiringToday()
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "No internet connection"
                is HttpException -> {
                    when (e.code()) {
                        in 400..499 -> "Client error occurred"
                        in 500..599 -> "Server error occurred"
                        else -> "An error occurred"
                    }
                }
                else -> "An error occurred"
            }
            emit(RequestState.Error(errorMessage))
        }
    }

    fun fetchSeries(): LiveData<RequestState<SeriesModel>> = liveData {
        emit(RequestState.Loading)
        try {
            val response = moviesRepository.fetchSeries()
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            val errorMessage = when (e) {
                is IOException -> "No internet connection"
                is HttpException -> {
                    when (e.code()) {
                        in 400..499 -> "Client error occurred"
                        in 500..599 -> "Server error occurred"
                        else -> "An error occurred"
                    }
                }
                else -> "An error occurred"
            }
            emit(RequestState.Error(errorMessage))
        }
    }
}