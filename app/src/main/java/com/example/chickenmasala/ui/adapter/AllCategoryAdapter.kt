package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.databinding.ItemKitchensBinding

class AllCategoryAdapter(private val list: List<CategoryEntity>) : RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kitchens,parent,false)
        return AllCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllCategoryViewHolder, position: Int) {
        val currentCategorey = list[position]
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentCategorey.imageUrl)
                .into(holder.binding.kitchenImage)
                kitchenName.text = currentCategorey.name

        }
    }

    override fun getItemCount() = list.size

    class AllCategoryViewHolder(viewItem: View) : CategotyAdapter.BaseViewHolder(viewItem) {
        val binding = ItemKitchensBinding.bind(viewItem)
    }
}