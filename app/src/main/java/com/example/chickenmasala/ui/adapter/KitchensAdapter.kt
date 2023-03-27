package com.example.chickenmasala.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.ItemKitchensBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class KitchensAdapter(private val list: List<RecipeEntity>, private val clickable: Clickable) :
    RecyclerView.Adapter<KitchensAdapter.KitchensViewHolder>() {
    class KitchensViewHolder(val binding: ItemKitchensBinding) : RecyclerView.ViewHolder(binding.root)

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchensViewHolder {
        val item = ItemKitchensBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KitchensViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: KitchensViewHolder, position: Int) {

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }

        holder.binding.apply {
            this.root.setOnClickListener { clickable.onClick(position) }
            kitchenName.text = list[position].cuisine
            Glide
                .with(this.root)
                .load(list[position].imageUrl)
                .placeholder(shimmerDrawable)
                .into(holder.binding.kitchenImage)

        }
    }

    interface Clickable {
        fun onClick(position: Int)
    }
}