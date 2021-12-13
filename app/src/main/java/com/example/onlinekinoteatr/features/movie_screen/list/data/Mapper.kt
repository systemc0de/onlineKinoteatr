package com.example.onlinekinoteatr.features.movie_screen.list.data

import com.example.onlinekinoteatr.features.movie_screen.list.data.api.model.Movies
import com.example.onlinekinoteatr.features.movie_screen.list.domain.model.MoviesDomainModel

fun Movies.toDomain() = MoviesDomainModel(
    id = id,
    title = title,
    description = description,
    year = year,
    poster = "https:$poster",
    countries = countries,
    genres = genres,
    raiting_kinopoisk = raiting_kinopoisk
)