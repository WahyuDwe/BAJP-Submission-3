package com.example.bajp_submission3.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bajp_submission3.databinding.FragmentMovieBinding
import com.example.bajp_submission3.viewmodel.ViewModelFactory
import com.example.bajp_submission3.vo.Status

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner) { movies ->
                if (movies != null) {
                    when(movies.status) {
                        Status.LOADING -> showProgressBar(true)

                        Status.SUCCESS -> {
                            showProgressBar(false)
                            movieAdapter.submitList(movies.data)
                        }

                        Status.ERROR -> {
                            showProgressBar(false)
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            with(binding?.rvMovie) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = movieAdapter
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding?.progressBar?.isVisible = state
        binding?.rvMovie?.isGone = state
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}