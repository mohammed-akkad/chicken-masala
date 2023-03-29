package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.databinding.ItemKitchensBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class AllCategoryAdapter(private val list: List<CategoryEntity>) :
    RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder>() {

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_kitchens, parent, false)
        return AllCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllCategoryViewHolder, position: Int) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }

        val currentCategorey = list[position]
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentCategorey.imageUrl)
                .placeholder(shimmerDrawable)
                .into(holder.binding.kitchenImage)
            kitchenName.text = currentCategorey.name

        }
    }

    override fun getItemCount() = list.size

    class AllCategoryViewHolder(viewItem: View) : CategotyAdapter.BaseViewHolder(viewItem) {
        val binding = ItemKitchensBinding.bind(viewItem)
    }
}