package com.example.bajp_submission3.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bajp_submission3.databinding.FragmentMovieFavoriteBinding
import com.example.bajp_submission3.viewmodel.ViewModelFactory


class MovieFavoriteFragment : Fragment() {
    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModel::class.java]
            val adapter = MovieFavoriteAdapter()

            viewModel.getFavMovies().observe(viewLifecycleOwner) { favMovie ->
                if (favMovie != null) {
                    adapter.submitList(favMovie)
                }
            }

            with(binding?.rvMovieFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}