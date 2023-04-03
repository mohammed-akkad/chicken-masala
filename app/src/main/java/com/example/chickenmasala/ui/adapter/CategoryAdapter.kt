package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.databinding.ItemCategorySmallBinding
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class CategoryAdapter(val list: List<CategoryEntity>, val listener: CategoryInteractionListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_small, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }
        val currentCategory = list[position]
        holder.binding.apply {
            Glide
                .with(this.root)
                .load(currentCategory.imageUrl)
                .placeholder(shimmerDrawable)
                .into(holder.binding.categoryImage)
            categoryTitle.text = currentCategory.name
            root.setOnClickListener { listener.onClickItemCategory(currentCategory.name) }
        }
    }

    override fun getItemCount() = list.size

    class CategoryViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemCategorySmallBinding.bind(viewItem)
    }

    companion object {
        private val shimmer =
            Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.5f)
                .setHighlightAlpha(0.7f)
                .setTilt(45f)
                .setAutoStart(true)
    }
}