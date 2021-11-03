package com.example.gagyeboost.ui.category

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gagyeboost.R
import com.example.gagyeboost.databinding.ItemCategoryBinding
import com.example.gagyeboost.model.data.Category

class CategoryAdapter : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(categoryItem: Category) {
            binding.category = categoryItem
            setCategoryColor()
        }

        private fun setCategoryColor() {
            with(this.itemView.context) {
                val emojiBackground = binding.tvEmoji.background as GradientDrawable
                val nameBackground = binding.tvCategoryName.background as GradientDrawable
                val colorId: Int = when (adapterPosition % 8) {
                    0 -> ContextCompat.getColor(this, R.color.category1)
                    1 -> ContextCompat.getColor(this, R.color.category2)
                    2 -> ContextCompat.getColor(this, R.color.category3)
                    3 -> ContextCompat.getColor(this, R.color.category4)
                    4 -> ContextCompat.getColor(this, R.color.category5)
                    5 -> ContextCompat.getColor(this, R.color.category6)
                    6 -> ContextCompat.getColor(this, R.color.category7)
                    7 -> ContextCompat.getColor(this, R.color.category8)
                    else -> ContextCompat.getColor(this, R.color.expense)
                }
                emojiBackground.setStroke(3, colorId)
                nameBackground.setStroke(3, colorId)
                emojiBackground.setColor(colorId)
            }
        }
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Category>() {

            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }

}