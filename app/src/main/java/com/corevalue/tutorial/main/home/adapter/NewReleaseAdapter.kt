package com.corevalue.tutorial.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.corevalue.tutorial.R
import com.corevalue.tutorial.data.entity.Movie
import com.corevalue.tutorial.databinding.ItemHomeNewReleaseBinding

class NewReleaseAdapter(private val itemList: List<Movie>) :
    RecyclerView.Adapter<NewReleaseAdapter.ReleaseHolder>() {

    inner class ReleaseHolder(
        private val binding: ItemHomeNewReleaseBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: Movie) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${s.poster_path}"
            binding.tvTitle.text = s.title
            Glide.with(binding.root).load(imageUrl).placeholder(R.drawable.bg_login_1).centerCrop()
                .into(binding.ivRelease)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReleaseHolder {
        return ReleaseHolder(
            ItemHomeNewReleaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ReleaseHolder, position: Int) {
        holder.bind(itemList[position])
    }
}