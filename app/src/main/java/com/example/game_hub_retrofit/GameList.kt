package com.example.game_hub_retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.game_hub_retrofit.databinding.ActivityGameListBinding
import com.example.game_hub_retrofit.models.Game
import com.example.game_hub_retrofit.models.GameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameList : AppCompatActivity() {
    private lateinit var binding: ActivityGameListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvGameList.layoutManager = LinearLayoutManager(this)

        loadGame()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun loadGame() {
        // 1. Specify the type in the Callback: Callback<GameResponse>
        ApiClient.api.getGames().enqueue(object : Callback<GameResponse> {

            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.isSuccessful) {
                    // Now you can safely access .results
                    val wrapper = response.body()
                    val gameList = wrapper?.results ?: emptyList()

                    binding.rvGameList.adapter = GameAdapter(gameList)
                } else {
                    Toast.makeText(this@GameList, "Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            // 2. IMPORTANT: This must also be Call<GameResponse> to match the interface
            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                Toast.makeText(this@GameList, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
