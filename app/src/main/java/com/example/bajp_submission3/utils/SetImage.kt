package com.example.bajp_submission3.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.bajp_submission3.BuildConfig


object SetImage {
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(BuildConfig.IMAGE_URL + url)
            .into(imageView)
    }
}
