package com.example.examenjugetesk.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.examenjugetesk.databinding.ItemMovieBinding
import com.example.examenjugetesk.model.Movie

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitulo.text = movie.nombre
            binding.tvFecha.text = "Fecha: ${movie.fecha_creacion}"
            binding.tvOrigen.text = "Origen: ${movie.origen}"
            binding.tvPelicula.text = "Película: ${movie.pelicula}"
            binding.tvDescripcion.text = movie.descripcion
            binding.tvColores.text = "Colores: ${movie.colores.joinToString(", ")}"
            binding.tvPrecio.text = "Precio: $${movie.precio}"
            binding.tvDisponibilidad.text =
                if (movie.disponibilidad) "Disponible" else "No disponible"

            Glide.with(binding.root.context)
                .load(movie.logo)
                .into(binding.ivPortada)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}
