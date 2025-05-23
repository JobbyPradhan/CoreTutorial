package com.corevalue.tutorial.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.corevalue.tutorial.R
import com.corevalue.tutorial.data.entity.newReleaseList
import com.corevalue.tutorial.databinding.FragmentHomeBinding
import com.corevalue.tutorial.main.home.adapter.NewReleaseAdapter


class HomeFragment : Fragment() {

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
        releaseAdapter = NewReleaseAdapter(newReleaseList)
        binding.recNewRelease.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = releaseAdapter

        }
    }

}