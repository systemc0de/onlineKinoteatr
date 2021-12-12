package com.example.onlinekinoteatr.features.movie_screen.list.ui

import com.example.onlinekinoteatr.base.Event
import com.example.onlinekinoteatr.features.movie_screen.list.domain.model.MoviesDomainModel

data class ViewState(
    val movies: List<MoviesDomainModel>,
    val errorMessage: String?,
    val isLoading: Boolean
) {
    val isInErrorState: Boolean = errorMessage != null
}

sealed class UiEvent : Event {
    object GetMovies : UiEvent()
    data class OnCardClick(val movie: MoviesDomainModel) : UiEvent()
}

sealed class DataEvent : Event {
    object OnLoadMovies : DataEvent()
    data class SuccessMoviesRequest(val movies: List<MoviesDomainModel>) : DataEvent()
    data class ErrorMoviesRequest(val errorMessage: String) : DataEvent()
}

sealed class SingleEvent : Event {
    data class OpenMovieCard(val movie: MoviesDomainModel) : SingleEvent()
}