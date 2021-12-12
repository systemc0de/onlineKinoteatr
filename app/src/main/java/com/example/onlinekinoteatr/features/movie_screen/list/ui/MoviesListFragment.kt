package com.example.onlinekinoteatr.features.movie_screen.list.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinekinoteatr.R
import com.example.onlinekinoteatr.databinding.FragmentMoviesListBinding
import com.example.onlinekinoteatr.features.movie_screen.detail.ui.MoviesCardFragment
import com.example.onlinekinoteatr.features.movie_screen.list.ui.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private val viewBinding: FragmentMoviesListBinding by viewBinding(FragmentMoviesListBinding::bind)

    private val moviesListViewModel by viewModel<MoviesListViewModel>()
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(movies = emptyList()) {
            moviesListViewModel.processUiEvent(UiEvent.OnCardClick(it))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.vpMovies.adapter = moviesAdapter

        moviesListViewModel.viewState.observe(viewLifecycleOwner, ::render)
        moviesListViewModel.singleLiveEvent.observe(viewLifecycleOwner, ::onSingleEvent)
    }

    private fun render(viewState: ViewState) {
        moviesAdapter.updateMovies(viewState.movies)
        viewBinding.pbLoading.isVisible = viewState.isLoading

    }

    private fun onSingleEvent(event: SingleEvent) {
        when (event) {
            is SingleEvent.OpenMovieCard -> {
                Log.d("", "CLICKED")
                parentFragmentManager.beginTransaction()
                    .replace(android.R.id.content, MoviesCardFragment.newInstance(event.movie))
                    .addToBackStack("movies")
                    .commit()
            }
        }
    }

}