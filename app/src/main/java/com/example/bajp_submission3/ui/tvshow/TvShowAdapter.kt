package com.example.bajp_submission3.ui.tvshow

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
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.databinding.ItemContentBinding
import com.example.bajp_submission3.ui.detail.DetailActivity
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.TV_SHOW


class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemContentBinding =
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemContentBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.title
                tvItemScore.text = tvShow.score.toString()
                tvItemDate.text = tvShow.date
                tvItemDesc.text = tvShow.description

                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + tvShow.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(ivItemPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, tvShow.id.toString())
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, TV_SHOW)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem == newItem

        }
    }
}