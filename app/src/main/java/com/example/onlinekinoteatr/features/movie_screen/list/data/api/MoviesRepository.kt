package com.example.onlinekinoteatr.features.movie_screen.list.data.api

import com.example.onlinekinoteatr.features.ui.movie_screen.list.domain.model.MoviesDomainModel

interface MoviesRepository {
    suspend fun getMovies():List<MoviesDomainModel>
}