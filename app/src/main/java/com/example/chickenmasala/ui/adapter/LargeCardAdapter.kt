package com.example.chickenmasala.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.databinding.ItemLargeCardBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


class LargeCardAdapter(
    private val list: List<Pair<String,String>>,
    private val onClick: (position: Int) -> Unit,
) :
    RecyclerView.Adapter<LargeCardAdapter.KitchensViewHolder>() {
    class KitchensViewHolder(val binding: ItemLargeCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val shimmer =
        Shimmer.AlphaHighlightBuilder()
            .setDuration(1800)
            .setBaseAlpha(0.5f)
            .setHighlightAlpha(0.7f)
            .setTilt(45f)
            .setAutoStart(true)


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

        holder.binding.apply {
            this.root.setOnClickListener { onClick(position) }
            itemTitle.text = list[position].first
            Glide
                .with(itemImage)
                .load(list[position].second)
                .placeholder(shimmerDrawable)
                .into(itemImage)

        }
    }
}