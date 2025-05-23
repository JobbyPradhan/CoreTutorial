package com.corevalue.tutorial.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.databinding.ItemHomeNewReleaseBinding

class NewReleaseAdapter(private val itemList:ArrayList<Movie>) :RecyclerView.Adapter<NewReleaseAdapter.ReleaseHolder>() {

    inner class ReleaseHolder(
        private val binding:ItemHomeNewReleaseBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(s: Movie) {
            binding.ivRelease.setImageResource(s.poster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseHolder {
        return ReleaseHolder(
            ItemHomeNewReleaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ReleaseHolder, position: Int) {
        holder.bind(itemList[position])
    }
}