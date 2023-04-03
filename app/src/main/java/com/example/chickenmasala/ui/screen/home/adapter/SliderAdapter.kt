package com.example.chickenmasala.ui.screen.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.ItemSliderBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class SliderAdapter(val list: List<RecipeEntity>) :
    RecyclerView.Adapter<SliderAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_slider, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }
        val currentRecipe = list[position]
        holder.binding.apply {
            Glide
                .with(sliderImage)
                .load(currentRecipe.imageUrl)
                .placeholder(shimmerDrawable)
                .into(sliderImage)
        }
    }

    override fun getItemCount() = list.size

    class RecipeViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemSliderBinding.bind(viewItem)
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