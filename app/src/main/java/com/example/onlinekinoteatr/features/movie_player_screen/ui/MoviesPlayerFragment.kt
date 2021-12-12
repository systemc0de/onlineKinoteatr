package com.example.onlinekinoteatr.features.movie_player_screen.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinekinoteatr.R
import com.example.onlinekinoteatr.databinding.FragmentMoviesPlayerBinding
import com.example.onlinekinoteatr.features.movie_screen.list.domain.model.MoviesDomainModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util

class MoviesPlayerFragment : Fragment(R.layout.fragment_movies_player) {

    private var exoPlayer: ExoPlayer? = null
    private val viewBinding: FragmentMoviesPlayerBinding by viewBinding(FragmentMoviesPlayerBinding::bind)

    companion object {
        private const val MOVIE_KEY = "movie"
        fun newInstance(movie: MoviesDomainModel) = MoviesPlayerFragment().apply {
            arguments = bundleOf(MOVIE_KEY to movie)
        }
    }

    private val movie: MoviesDomainModel by lazy {
        requireArguments().getParcelable(MOVIE_KEY)!!
    }


    private fun initializePlayer() {
        //Log.d("VIDEVA", movie.trailer!!)
        exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            viewBinding.pvMoviePlayer.player = this
            setMediaSource(buildMediaSource())
            playWhenReady = true
            prepare()
        }
    }

    private fun buildMediaSource() = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
        .createMediaSource(MediaItem.fromUri("https://www.youtube.com/embed/5snjgB9PO48"))

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT > 23) initializePlayer()
    }

    override fun onResume() {
        super.onResume()

        if (Util.SDK_INT <= 23) initializePlayer()
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT <= 23) releasePlayer()
    }

    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) releasePlayer()
    }

    private fun releasePlayer() {
        if (exoPlayer == null) {
            return
        }
        exoPlayer!!.release()
        exoPlayer = null
    }
}