package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R

import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.CardLargeBinding
import com.example.chickenmasala.databinding.CardSmallBinding

class RecipesAdapter(val list: List<RecipeEntity>) :
    RecyclerView.Adapter<RecipesAdapter.BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {

            VIEW_TYPE_IMAGE_LARGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_large, parent, false)
                RecipeViewHolder(view)
            }
            VIEW_TYPE_FOR_YOU -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.card_small, parent, false)
                return ForYouViewHolder(view)
            }

            else -> {
                super.createViewHolder(parent, viewType)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when {
            holder is RecipeViewHolder && position > 0 -> bindImage(holder, position)
            holder is ForYouViewHolder -> bindRecipeForYou(holder, position)
        }


    }

    override fun getItemCount() = list.size

    private fun bindImage(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = list[position]
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentRecipe.imageUrl)
                .into(holder.binding.imageCardLarge)
        }


    }



        private fun bindRecipeForYou(holder: ForYouViewHolder, position: Int) {
        val currentRecipe = list[position]
        holder.binding.apply {
            textNameRecipe.text = currentRecipe.name

            Glide
                .with(this.root)
                .load(currentRecipe.imageUrl)
                .into(holder.binding.imageRecipe)

        }

    }


    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class RecipeViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardLargeBinding.bind(viewItem)
    }

    class ForYouViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardSmallBinding.bind(viewItem)
    }

    companion object {
        const val VIEW_TYPE_IMAGE_LARGE = 0
        const val VIEW_TYPE_FOR_YOU = 1

    }


}