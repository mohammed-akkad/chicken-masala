package com.example.chickenmasala.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.FoodDataSource
import com.example.chickenmasala.data.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.CardCategoryBinding
import com.example.chickenmasala.databinding.CardLargeBinding
import com.example.chickenmasala.util.Constants

class RecipesAdapter(val list: List<RecipeEntity>) :
    RecyclerView.Adapter<RecipesAdapter.BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_large, parent, false)
                RecipeViewHolder(view)
            }
            //            VIEW_TYPE_MATCH -> {
            //                val view =
            //                    LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
            //                return MatchViewHolder(view)
            //            }
            else -> {
                super.createViewHolder(parent, viewType)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when (holder) {
            is RecipeViewHolder -> bindImage(holder, position)
            is CategoryViewHolder -> bindCategory(holder, position)
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
    private fun bindCategory(holder: CategoryViewHolder, position: Int) {
        val currentCategory = list[position]
        holder.binding.apply {
    
            Glide
                .with(this.root)
                .load(currentCategory.imageUrl)
                .into(holder.binding.imageView)
        }

    }

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class RecipeViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardLargeBinding.bind(viewItem)
    }

    class CategoryViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardCategoryBinding.bind(viewItem)
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
    }


}