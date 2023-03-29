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
import com.example.chickenmasala.databinding.ItemCardFoodBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class SearchAdapter(val list: List<RecipeEntity>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_food, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }

        val currentItem = list[position]

        holder.binding.apply {
            this.root.setOnClickListener {  }
            textviewRecipe.text = currentItem.name
            Glide
                .with(this.root)
                .load(currentItem.imageUrl)
                .placeholder(shimmerDrawable)
                .into(holder.binding.imageviewIndianFoodFirstItem)

        }

    }

    override fun getItemCount() = list.size


    class SearchViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = ItemCardFoodBinding.bind(viewItem)


    }
}