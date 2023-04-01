package com.example.chickenmasala.ui.adapter

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
    RecyclerView.Adapter<SliderAdapter.BaseViewHolder>() {

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {

            VIEW_TYPE_IMAGE_LARGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_slider, parent, false)
                RecipeViewHolder(view)
            }

            else -> {
                super.createViewHolder(parent, viewType)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when {
            holder is RecipeViewHolder -> bindImage(holder, position)
        }


    }

    override fun getItemCount() = list.size

    private fun bindImage(holder: RecipeViewHolder, position: Int) {
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






    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class RecipeViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemSliderBinding.bind(viewItem)
    }


    companion object {
        const val VIEW_TYPE_IMAGE_LARGE = 0

    }


}