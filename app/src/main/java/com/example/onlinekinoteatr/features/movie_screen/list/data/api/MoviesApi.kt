package com.example.onlinekinoteatr.features.movie_screen.list.data.api

import com.example.onlinekinoteatr.features.movie_screen.list.data.api.model.MoviesModel
import retrofit2.http.GET

interface MoviesApi {
    //@GET("onlineKinoteatr/kinopoisk.json")
    //@GET("LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/")
    @GET("OnlineCinema/Network/kinopoiskjson.json")
    suspend fun getMovies(): MoviesModel
}