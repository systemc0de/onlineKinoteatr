package com.example.onlinekinoteatr.features.movie_screen.list.ui

import com.example.onlinekinoteatr.base.BaseViewModel
import com.example.onlinekinoteatr.base.Event
import com.example.onlinekinoteatr.base.SingleLiveEvent
import com.example.onlinekinoteatr.features.movie_screen.list.domain.MoviesInteractor

class MoviesListViewModel(private val moviesInteractor: MoviesInteractor) :
    BaseViewModel<ViewState>() {

    val singleLiveEvent = SingleLiveEvent<SingleEvent>()

    init {
        processUiEvent(UiEvent.GetMovies)
    }

    override fun initialViewState(): ViewState = ViewState(
        movies = emptyList(),
        errorMessage = null,
        isLoading = false
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {

        when (event) {
            is UiEvent.GetMovies -> {
                processDataEvent(DataEvent.OnLoadMovies)
                moviesInteractor.getMovies().fold(
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessMoviesRequest(it))
                    },
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorMoviesRequest(
                                it.localizedMessage ?: ""
                            )
                        )
                    }
                )
            }

            is DataEvent.OnLoadMovies -> {
                return previousState.copy(isLoading = !previousState.isLoading)
            }

            is UiEvent.OnCardClick -> {
                singleLiveEvent.value = SingleEvent.OpenMovieCard(event.movie)
            }

            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(
                    movies = event.movies,
                    isLoading = false,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
        }
        return null

    }
}