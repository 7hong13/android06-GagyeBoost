package com.example.gagyeboost.ui.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gagyeboost.databinding.ItemRvDetailBinding
import com.example.gagyeboost.model.data.DateDetailItem

class DetailAdapter(private val longClickListener: (Int) -> (Boolean)) :
    ListAdapter<DateDetailItem, DetailAdapter.DetailViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemRvDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DetailViewHolder(private val binding: ItemRvDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var item: DateDetailItem

        init {
            binding.constraintItemDetail.setOnLongClickListener {
                if (::item.isInitialized) longClickListener(item.id)
                else false
            }
        }

        fun bind(item: DateDetailItem) {
            this.item = item
            binding.item = item
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DateDetailItem>() {
            override fun areContentsTheSame(oldItem: DateDetailItem, newItem: DateDetailItem) =
                newItem == oldItem

            override fun areItemsTheSame(oldItem: DateDetailItem, newItem: DateDetailItem) =
                newItem.id == oldItem.id
        }
    }

}