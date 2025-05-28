package com.corevalue.tutorial.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.data.repository.MovieRepository
import com.corevalue.tutorial.databinding.FragmentHomeBinding
import com.corevalue.tutorial.main.home.adapter.NewReleaseAdapter
import com.corevalue.tutorial.viewmodel.MovieViewModel


class HomeFragment : Fragment() {


    private val viewModel: MovieViewModel by activityViewModels()
    private lateinit var releaseAdapter : NewReleaseAdapter
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return MovieViewModel(repository) as T
//            }
//        })[MovieViewModel::class.java]


        viewModel.getNowPlayingMovie()
        viewModel.movies.observe(viewLifecycleOwner) {
                initRec(it)
        }

    }

    private fun initRec(movies: List<Movie>) {
        releaseAdapter = NewReleaseAdapter(movies)
        binding.recNewRelease.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = releaseAdapter

        }
    }

}