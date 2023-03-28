package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.CardMediumBinding
import com.example.chickenmasala.databinding.CardSmallBinding
import com.example.chickenmasala.databinding.ItemKitchensBinding
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.ui.screen.ForYouFragment

class ForYouRecipesAdapter(val list: List<RecipeEntity>, val listener: RecipeInteractionListener) : RecyclerView.Adapter<ForYouRecipesAdapter.ForYouRecipesViewHolder>(){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouRecipesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.card_medium , parent , false)
        return ForYouRecipesViewHolder(view)
    }


    override fun onBindViewHolder(holder: ForYouRecipesViewHolder, position: Int) {
        val currentRecipes = list[position]
        holder.binding.apply {
            textNameRecipe.text = currentRecipes.name

            root.setOnClickListener{
                listener.onClickItemRecipeEntitty(currentRecipes)

            }


            Glide.with(this.root)
                .load(currentRecipes.imageUrl)
                .placeholder(R.drawable.cloud_download)
                .into(imageRecipe)

        }
        }


    override fun getItemCount() = list.size

    class ForYouRecipesViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val binding = CardMediumBinding.bind(viewItem)

    }
}

