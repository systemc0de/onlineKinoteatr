package com.example.onlinekinoteatr.features.ui.movie_screen.list.data.api.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("id_kinopoisk") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("year") val year: Int?,
    @SerializedName("poster") val poster: String?,
    @SerializedName("countries") val countries: List<String>?,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("rating_kinopoisk") val raiting_kinopoisk: String?
)
