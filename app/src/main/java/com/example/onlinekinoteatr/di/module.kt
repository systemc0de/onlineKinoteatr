package com.example.onlinekinoteatr.di

import com.example.onlinekinoteatr.features.movie_screen.list.domain.MoviesInteractor
import com.example.onlinekinoteatr.features.movie_screen.list.data.api.MoviesApi
import com.example.onlinekinoteatr.features.movie_screen.list.data.api.MoviesRemoteSource
import com.example.onlinekinoteatr.features.movie_screen.list.data.api.MoviesRepository
import com.example.onlinekinoteatr.features.movie_screen.list.data.api.MoviesRepositoryImpl
import com.example.onlinekinoteatr.features.movie_screen.list.ui.MoviesListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://raw.githubusercontent.com/systemc0de/"

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY}).build()
    }


    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create()
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get<MoviesRemoteSource>())
    }


    single<MoviesInteractor> {
        MoviesInteractor(get<MoviesRepository>())
    }

    viewModel<MoviesListViewModel> {
        MoviesListViewModel(get<MoviesInteractor>())
    }

}
