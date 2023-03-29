package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.CardSmallBinding
import com.example.chickenmasala.ui.listener.SpecialTreatsListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class CakeAdapter(val list: List<RecipeEntity>, val listener: SpecialTreatsListener) :
    RecyclerView.Adapter<CakeAdapter.CakeViewHolder>() {

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_medium, parent, false)
        return CakeViewHolder(view)

    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        val currentItem = list[position]
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentItem.imageUrl)
                .placeholder(shimmerDrawable)
                .into(holder.binding.imageRecipe)

            textNameRecipe.text = currentItem.name
            root.setOnClickListener { listener.onClickItemRecipeEntity(currentItem.name) }
        }

    }

    override fun getItemCount() = list.size

    class CakeViewHolder(viewItem : View) : ViewHolder(viewItem)  {
        val binding = CardSmallBinding.bind(viewItem)

    }

}