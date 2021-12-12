package com.example.onlinekinoteatr.features.movie_screen.list.domain.model

import com.example.onlinecinema.base.attempt
import com.example.onlinekinoteatr.features.movie_screen.list.data.api.MoviesRepository

class MoviesInteractor (private val moviesRepository: MoviesRepository) {
    suspend fun getMovies() = attempt {
        moviesRepository.getMovies()
    }
}