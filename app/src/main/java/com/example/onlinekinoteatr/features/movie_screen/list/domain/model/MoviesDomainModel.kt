package com.example.onlinekinoteatr.features.movie_screen.list.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomainModel(
    val id: Int,
    val title: String?,
    val description: String?,
    val year: Int?,
    val poster: String?,
    val countries: List<String>?,
    val genres: List<String>?,
    val raiting_kinopoisk: String?
) : Parcelable