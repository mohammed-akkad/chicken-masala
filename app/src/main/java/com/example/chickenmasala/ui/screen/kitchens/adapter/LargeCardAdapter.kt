package com.example.chickenmasala.ui.screen.kitchens.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.databinding.ItemLargeCardBinding
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


class LargeCardAdapter(
    private val list: List<Pair<String,String>>,
    private val listener: CategoryInteractionListener,
) :
    RecyclerView.Adapter<LargeCardAdapter.KitchensViewHolder>() {
    class KitchensViewHolder(val binding: ItemLargeCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitchensViewHolder {
        val item = ItemLargeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KitchensViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: KitchensViewHolder, position: Int) {
        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer.build())
        }
        val currentKitchen = list[position]
        holder.binding.apply {
            this.root.setOnClickListener { listener.onClickItemCategory(currentKitchen.first) }
            itemTitle.text = currentKitchen.first
            Glide
                .with(itemImage)
                .load(currentKitchen.second)
                .placeholder(shimmerDrawable)
                .into(itemImage)
        }
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