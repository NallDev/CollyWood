package com.nalldev.collywood.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val posterPath : String = "",
    val title : String = "",
    val overview : String = "",
    val date : String = "",
    val vote : String = "",
    val genre : List<String> = listOf(),
) : Parcelable