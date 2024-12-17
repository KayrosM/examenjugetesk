package com.example.examenjugetesk

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenjugetesk.adapter.MovieAdapter
import com.example.examenjugetesk.databinding.ActivityMainBinding
import com.example.examenjugetesk.model.Movie
import com.example.examenjugetesk.network.ApiClient
import com.example.examenjugetesk.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val movieList = mutableListOf<Movie>()
    private val apiService by lazy { ApiClient.retrofit.create(ApiService::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchMovies()
    }

    private fun setupRecyclerView() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchMovies() {
        val ids = listOf(1, 2, 3, 4, 5, 6, 7)
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch(Dispatchers.IO) {
            // Realiza las solicitudes en paralelo
            val deferredMovies = ids.map { id ->
                async { apiService.getMovieDetails(id).execute() }
            }

            deferredMovies.forEach { deferred ->
                val response = deferred.await()
                if (response.isSuccessful) {
                    response.body()?.let { movieList.add(it) }
                }
            }

            withContext(Dispatchers.Main) {
                binding.progressBar.visibility = View.GONE
                movieAdapter = MovieAdapter(movieList)
                binding.rvMovies.adapter = movieAdapter
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
