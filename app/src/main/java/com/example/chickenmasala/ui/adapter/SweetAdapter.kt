package com.example.chickenmasala.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.CardCategoryBinding
import com.example.chickenmasala.databinding.CardSmallBinding

class SweetAdapter(val list: List<RecipeEntity>) :
    RecyclerView.Adapter<SweetAdapter.SweetViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SweetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_small, parent, false)
        return SweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SweetViewHolder, position: Int) {
        val currentItem = list[position]

        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentItem.imageUrl)
                .into(holder.binding.imageRecipe)

            textNameRecipe.text = currentItem.name
        }




    }

    override fun getItemCount() = list.size

    class SweetViewHolder(viewItem: View) : ViewHolder(viewItem) {
        val binding = CardSmallBinding.bind(viewItem)
    }

}