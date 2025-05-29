package com.corevalue.tutorial.main.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.data.local.AppDatabase
import com.corevalue.tutorial.data.repository.MovieRepository
import com.corevalue.tutorial.databinding.FragmentHomeBinding
import com.corevalue.tutorial.main.home.adapter.NewReleaseAdapter
import com.corevalue.tutorial.viewmodel.MovieViewModel
import com.corevalue.tutorial.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {


    private lateinit var viewModel: MovieViewModel

    //by activityViewModels()
    private lateinit var releaseAdapter: NewReleaseAdapter
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = AppDatabase.getDatabase(requireContext()).getMovieDao()
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val repository = MovieRepository(dao,connectivityManager) // or inject this
        val factory = MovieViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]


        viewModel.getNowPlayingMovie()
        lifecycleScope.launch {
            viewModel.movieStateFlow.collectLatest {
                if (it.isLoading) {
                    //show Loading
                } else if (it.error.isNotEmpty()) {
                    //show error
                } else {
                    initRec(it.movieList)
                }
            }
        }


    }

    private fun initRec(movies: List<Movie>) {
        releaseAdapter = NewReleaseAdapter(movies)
        binding.recNewRelease.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = releaseAdapter

        }
    }

}