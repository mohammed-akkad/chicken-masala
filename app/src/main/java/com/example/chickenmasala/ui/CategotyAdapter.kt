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
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.databinding.ItemCardFoodBinding

class CategotyAdapter(val list: List<CategoryEntity>, val listener: CategoryInteractionListener) :
    RecyclerView.Adapter<CategotyAdapter.BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.card_category, parent, false)
                CategoryViewHolder(view)
            }
//            VIEW_TYPE_MATCH -> {
//                val view =
//                    LayoutInflater.from(parent.context).inflate(R.layout.item_card_food, parent, false)
//                return CategorySpicficViewHolder(view)
//            }
            else -> {
                super.createViewHolder(parent, viewType)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {


        when (holder) {
            is CategoryViewHolder -> bindCategory(holder, position)
//            is CategorySpicficViewHolder -> bindCategorySpicfic(holder, position)
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
            textNameCategory.setOnClickListener { listener.onClickItemCategory(currentCategory.name) }
        }

    }

//    private fun bindCategorySpicfic(holder: CategorySpicficViewHolder, position: Int) {
//        val currentCategory = list[position]
//        holder.binding.apply {
//
//            Glide
//                .with(this.root)
//                .load(currentCategory.imageUrl)
//                .into(holder.binding.imageCategoryFood)
//            textCategoryFood.text = currentCategory.name
//
//        }
//
//    }

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)


    class CategoryViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = CardCategoryBinding.bind(viewItem)
    }

//    class CategorySpicficViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
//        val binding = ItemCardFoodBinding.bind(viewItem)
//    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
//        const val VIEW_TYPE_MATCH = 1
    }
}