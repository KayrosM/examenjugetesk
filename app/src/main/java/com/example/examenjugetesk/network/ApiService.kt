package com.example.examenjugetesk.network

import com.example.examenjugetesk.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{id}") // Consulta directa usando el ID como parámetro dinámico
    fun getMovieDetails(@Path("id") id: Int): Call<Movie>
}
