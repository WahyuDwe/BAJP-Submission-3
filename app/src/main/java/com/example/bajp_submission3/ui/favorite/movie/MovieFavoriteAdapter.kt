package com.example.bajp_submission3.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bajp_submission3.BuildConfig
import com.example.bajp_submission3.R
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.databinding.ItemContentBinding
import com.example.bajp_submission3.ui.detail.DetailActivity
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.MOVIE

class MovieFavoriteAdapter :
    PagedListAdapter<MovieEntity, MovieFavoriteAdapter.MovieFavoriteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavoriteViewHolder {
        val itemContentBinding =
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFavoriteViewHolder(itemContentBinding)
    }

    override fun onBindViewHolder(holder: MovieFavoriteViewHolder, position: Int) {
        val movieFavorite = getItem(position)
        if (movieFavorite != null) {
            holder.bind(movieFavorite)
        }
    }


    inner class MovieFavoriteViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemScore.text = movie.score.toString()
                tvItemDate.text = movie.date
                tvItemDesc.text = movie.description

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + movie.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivItemPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, MOVIE)

                    itemView.context.startActivity(intent)

                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem

        }
    }
}