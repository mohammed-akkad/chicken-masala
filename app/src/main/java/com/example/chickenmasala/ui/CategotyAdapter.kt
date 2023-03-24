package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.databinding.CardCategoryBinding
import com.example.chickenmasala.databinding.CardLargeBinding

class CategotyAdapter(val list: List<CategoryEntity>) : RecyclerView.Adapter<CategotyAdapter.BaseViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_category, parent, false)
                CategoryViewHolder(view)
            }
            //            VIEW_TYPE_MATCH -> {
            //                val view =
            //                    LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
            //                return MatchViewHolder(view)
            //            }
            else -> {
                super.createViewHolder(parent, viewType)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when (holder) {
            is CategoryViewHolder -> bindCategory(holder, position)
        }

    }

    override fun getItemCount() = list.size


    private fun bindCategory(holder: CategoryViewHolder, position: Int) {
        val currentCategory = list[position]
        holder.binding.apply {

            Glide
                .with(this.root)
                .load(currentCategory.imageUrl)
                .into(holder.binding.imageView)
            textNameCategory.text = currentCategory.name
        }

    }

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)


    class CategoryViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardCategoryBinding.bind(viewItem)
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
    }
}