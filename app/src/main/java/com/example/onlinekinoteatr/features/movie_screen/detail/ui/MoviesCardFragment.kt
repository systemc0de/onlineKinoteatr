package com.example.onlinekinoteatr.features.movie_screen.detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinekinoteatr.R
import com.example.onlinekinoteatr.base.loadImage
import com.example.onlinekinoteatr.databinding.FragmentMoviesCardBinding
import com.example.onlinekinoteatr.features.movie_player_screen.ui.MoviesPlayerFragment
import com.example.onlinekinoteatr.features.movie_screen.list.domain.model.MoviesDomainModel

class MoviesCardFragment : Fragment(R.layout.fragment_movies_card) {

    private val viewBinding: FragmentMoviesCardBinding by viewBinding(FragmentMoviesCardBinding::bind)

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesCardFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivCard.loadImage(movie.poster)
            tvCardGenres.text =
                movie.genres?.joinToString(separator = ", ")
            tvCardCountries.text = movie.countries?.joinToString(separator = ", ")
            tvCardDescription.text = movie.description
            tvCardRating.text = movie.raiting_kinopoisk
            tvCardYear.text = movie.year.toString()
            tbCardTitle.title = movie.title
//            tbCardTitle2.text = movie.title

            fabCard.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviesPlayerFragment.newInstance(movie))
                    .addToBackStack("movies")
                    .commit()
            }

        }


    }


}