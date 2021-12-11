package com.example.onlinekinoteatr.features.ui.movie_screen.list.data.api.model

import com.google.gson.annotations.SerializedName

data class MoviesModel (@SerializedName("movies") val results: List<Movies>)