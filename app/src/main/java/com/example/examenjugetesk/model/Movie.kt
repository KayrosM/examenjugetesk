package com.example.examenjugetesk.model

data class Movie(
    val id: Int,
    val nombre: String,
    val logo: String,
    val fecha_creacion: String,
    val origen: String,
    val pelicula: String,
    val descripcion: String,
    val colores: List<String>,
    val disponibilidad: Boolean,
    val precio: Int
)
