package com.example.game_hub_retrofit.models

data class Game(
    val id: Int,
    val name: String,
    val background_image: String,
    val ratings_count: Int,
    val metacritic: Int,
    val genres: List<Genre>
)
