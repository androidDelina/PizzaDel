package com.example.pizzadel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pizzadel.pojo.Pizza

class PizzaItemDiffCallback: DiffUtil.ItemCallback<Pizza>() {
    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
        return oldItem == newItem
    }
}