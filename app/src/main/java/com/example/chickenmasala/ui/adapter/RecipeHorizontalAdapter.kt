package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.ItemRecipeHorizontalBinding
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class RecipeHorizontalAdapter(
    val list: List<RecipeEntity>,
    val listener: RecipeInteractionListener
) :
    RecyclerView.Adapter<RecipeHorizontalAdapter.CategorySpicficViewHolder>() {


    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategorySpicficViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_horizontal, parent, false)
        return CategorySpicficViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategorySpicficViewHolder,
        position: Int
    ) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }

        val currentRecipe = list[position]
        holder.binding.apply {

            Glide
                .with(recipeImage)
                .load(currentRecipe.imageUrl)
                .placeholder(shimmerDrawable)
                .into(recipeImage)
            recipeTitle.text = currentRecipe.name
            recipeCuisine.text = currentRecipe.cuisine
            timeToPrepare.text=currentRecipe.totalTime.toString()+" mins"
            root.setOnClickListener { listener.onClickItemRecipeEntitty(currentRecipe) }

        }
    }

    override fun getItemCount() = list.size

    class CategorySpicficViewHolder(viewItem: View) : CategotyAdapter.BaseViewHolder(viewItem) {
        val binding = ItemRecipeHorizontalBinding.bind(viewItem)
    }
}