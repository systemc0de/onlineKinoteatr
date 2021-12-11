package com.example.onlinekinoteatr.features.ui.movie_screen.list.data

import com.example.onlinekinoteatr.features.ui.movie_screen.list.data.api.model.MoviesModel

class MoviesRemoteSource(private val moviesApi: MoviesApi) {
    suspend fun getMovies(): MoviesModel = moviesApi.getMovies()
}