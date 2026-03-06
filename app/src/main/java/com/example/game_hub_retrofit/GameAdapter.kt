package com.example.game_hub_retrofit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.game_hub_retrofit.databinding.GameCardBinding
import com.example.game_hub_retrofit.models.Game

class GameAdapter(private val list: List<Game>) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    private var lastPosition = -1
    class ViewHolder(val binding: GameCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = GameCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size


    private fun setAnimation(viewToAnimate: View, position: Int) {
        // Only animate if the item is newly appearing
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_animation)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val game = list[position]


        holder.binding.gameTitle.text =  game.name
//        holder.binding.gameScore.text = game.ratings_count.toString()
//        holder.binding.gameCategory.text = game.genres.toString()
        holder.binding.metaScore.text = game.metacritic.toString()



        Glide.with(holder.itemView.context)
            .load(game.background_image)
            .into(holder.binding.gameImage)


        setAnimation(holder.itemView, position)

    }
}