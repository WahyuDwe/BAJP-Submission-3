package com.example.bajp_submission3.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bajp_submission3.databinding.FragmentTvShowFavoriteBinding
import com.example.bajp_submission3.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment() {
    private var _binding: FragmentTvShowFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowFavoriteViewModel::class.java]
            val adapter = TvShowFavoriteAdapter()

            viewModel.getFavTvShow().observe(viewLifecycleOwner) { favTvShow ->
                if (favTvShow != null) {
                    adapter.submitList(favTvShow)
                }
            }

            binding?.rvTvshowFavorite?.apply {
                setHasFixedSize(true)
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}