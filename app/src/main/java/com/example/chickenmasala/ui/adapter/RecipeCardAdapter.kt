package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.ItemRecipeBinding
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class RecipeCardAdapter(val list: List<RecipeEntity>, val listener: RecipeInteractionListener) : RecyclerView.Adapter<RecipeCardAdapter.ForYouRecipesViewHolder>(){

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouRecipesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_recipe , parent , false)
        return ForYouRecipesViewHolder(view)
    }


    override fun onBindViewHolder(holder: ForYouRecipesViewHolder, position: Int) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }


        val currentRecipes = list[position]
        holder.binding.apply {
            recipeNameText.text = currentRecipes.name

            root.setOnClickListener{
                listener.onClickItemRecipeEntitty(currentRecipes)

            }


            Glide.with(recipeImageUrl)
                .load(currentRecipes.imageUrl)
                .placeholder(shimmerDrawable)
                .into(recipeImageUrl)

        }
        }


    override fun getItemCount() = list.size

    class ForYouRecipesViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val binding = ItemRecipeBinding.bind(viewItem)



    }
}

