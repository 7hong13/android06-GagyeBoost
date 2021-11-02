package com.example.gagyeboost.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gagyeboost.databinding.ItemDateBinding
import java.text.DecimalFormat

class CustomCalendarAdapter(private val itemClickListener: (String) -> Unit) :
    RecyclerView.Adapter<CustomCalendarAdapter.DateViewHolder>() {
    private val calendar = CustomCalendar()
    private val dateItemList = mutableListOf<DateItem>()

    init {
        calendar.datesInMonth.forEach {
            dateItemList.add(DateItem((0..10000).random(), (0..10000).random(), it))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            ItemDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val date = dateItemList[position].date.toString()
        val layoutParams = GridLayoutManager.LayoutParams(holder.itemView.layoutParams)
        layoutParams.height = layoutParams.width

        with(holder) {
            bind(dateItemList[position])
            itemView.setOnClickListener { itemClickListener.invoke(date) }
            itemView.requestLayout()
        }
    }

    override fun getItemCount() = dateItemList.size

    inner class DateViewHolder(private val binding: ItemDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val dec = DecimalFormat("#,###")

        fun bind(dateItem: DateItem) {
            setDate(dateItem.date.toString())
            setMoney(binding.tvEarnings, dateItem.income)
            setMoney(binding.tvExpense, dateItem.expense)
        }

        private fun setMoney(textView: TextView, money: Int) {
            if (money == 0) {
                textView.isGone = true
            } else {
                textView.text = dec.format(money)
            }
        }

        private fun setDate(date: String) {
            with(binding) {

                when (adapterPosition % CustomCalendar.DAYS_OF_WEEK) {
                    0 -> tvDate.setTextColor(Color.parseColor("#D96D84"))
                    6 -> tvDate.setTextColor(Color.parseColor("#6395e6"))
                    else -> tvDate.setTextColor(Color.parseColor("#676d6e"))
                }

                if (adapterPosition < calendar.prevMonthTailOffset
                    || adapterPosition >= calendar.prevMonthTailOffset + calendar.currentMonthMaxDate
                ) {
                    tvDate.alpha = 0.3f
                } else {
                    tvDate.alpha = 1f
                }
                tvDate.text = date
            }
        }
    }
}
