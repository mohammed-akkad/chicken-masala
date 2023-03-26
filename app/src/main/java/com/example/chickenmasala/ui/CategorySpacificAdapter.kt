package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.ItemCardFoodBinding

class CategorySpacificAdapter(val list: List<RecipeEntity>, val listener: RecipeInteractionListener) :
    RecyclerView.Adapter<CategorySpacificAdapter.CategorySpicficViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategorySpicficViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_food, parent, false)
        return CategorySpicficViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategorySpicficViewHolder,
        position: Int
    ) {
        val currentCategory = list[position]
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentCategory.imageUrl)
                .into(holder.binding.imageCategoryFood)
            textCategoryFood.text = currentCategory.name
            root.setOnClickListener { listener.onClickItemRecipeEntitty(currentCategory)  }

        }
    }

    override fun getItemCount() = list.size

    class CategorySpicficViewHolder(viewItem: View) : CategotyAdapter.BaseViewHolder(viewItem) {
        val binding = ItemCardFoodBinding.bind(viewItem)
    }
}