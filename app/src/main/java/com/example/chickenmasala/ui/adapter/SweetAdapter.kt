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

class SweetAdapter(val list: List<RecipeEntity>, val listener : SpecialTreatsListener) :
    RecyclerView.Adapter<SweetAdapter.SweetViewHolder>() {

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SweetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_medium, parent, false)
        return SweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SweetViewHolder, position: Int) {
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

    class SweetViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = CardSmallBinding.bind(viewItem)
    }

}