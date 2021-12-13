package com.example.onlinekinoteatr.features.movie_screen.list.data.api

import com.example.onlinekinoteatr.features.movie_screen.list.domain.model.MoviesDomainModel
import com.example.onlinekinoteatr.features.movie_screen.list.data.toDomain

class MoviesRepositoryImpl(
    private val moviesRemoteSource: MoviesRemoteSource,
) : MoviesRepository {
    override suspend fun getMovies(): List<MoviesDomainModel> {
        return moviesRemoteSource.getMovies().results.map { it.toDomain() }
    }
}