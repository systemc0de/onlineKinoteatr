package com.example.onlinekinoteatr.features.movie_screen.list.data.api

import com.example.onlinekinoteatr.features.ui.movie_screen.list.data.api.model.MoviesModel
import retrofit2.http.GET

interface MoviesApi {
    @GET("onlineKinoteatr/cinema_structure/kinopoisk.json")
    suspend fun getMovies(): MoviesModel
}