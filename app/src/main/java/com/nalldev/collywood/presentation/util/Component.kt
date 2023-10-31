package com.nalldev.collywood.presentation.util

import com.nalldev.collywood.domain.model.DetailModel
import com.nalldev.collywood.domain.model.ResultsMovie
import com.nalldev.collywood.domain.model.ResultsSeries
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Component {
    fun changeDateToYear(dateString: String) : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(dateString)
            val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            yearFormat.format(date)
        } catch (e: Exception) {
            "XXXX"
        }
    }

    fun changeDateToMMMMDDYYYY(dateString: String) : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(dateString)
            val yearFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            yearFormat.format(date)
        } catch (e: Exception) {
            "XXXX"
        }
    }

    fun formatDoubleToOneDecimal(value: Double): String {
        val decimalFormat = DecimalFormat("#.#")
        return decimalFormat.format(value)
    }

    fun itemToModel(item: ResultsMovie?): DetailModel {
        if (item != null) {
            val data = item.genreIds
            val dataGenres = DataMovies.genreList

            val listGenres = data.mapNotNull { genreId ->
                dataGenres.find { it.first == genreId }?.second
            }

            return DetailModel(
                posterPath = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.posterPath}",
                title = item.title,
                overview = item.overview,
                date = changeDateToMMMMDDYYYY(item.releaseDate),
                vote = formatDoubleToOneDecimal(item.voteAverage),
                genre = listGenres
            )
        } else {
            return DetailModel()
        }
    }

    fun itemToModel(item: ResultsSeries?): DetailModel {
        if (item != null) {
            val data = item.genreIds
            val dataGenres = DataMovies.genreList

            val listGenres = data.mapNotNull { genreId ->
                dataGenres.find { it.first == genreId }?.second
            }

            return DetailModel(
                posterPath = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${item.posterPath}",
                title = item.name,
                overview = item.overview,
                date = changeDateToMMMMDDYYYY(item.firstAirDate),
                vote = formatDoubleToOneDecimal(item.voteAverage),
                genre = listGenres
            )
        } else {
            return DetailModel()
        }
    }
}