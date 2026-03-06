package com.example.game_hub_retrofit

import com.example.game_hub_retrofit.models.Game
import com.example.game_hub_retrofit.models.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//val apiKey = BuildConfig.API_KEY


interface ApiService {
    @GET("games")
    fun getGames(@Query("key") apiKey: String = "ca1c6808d88b4d96bae3187acbc3d0cb"): Call<GameResponse>
}