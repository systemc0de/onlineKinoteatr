package com.example.onlinekinoteatr.features.ui.movie_screen.list.data

import com.example.onlinekinoteatr.features.ui.movie_screen.list.domain.model.MoviesDomainModel

interface MoviesRepository {
    suspend fun getMovies():List<MoviesDomainModel>
}