package com.example.pizzadel.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pizzadel.pojo.Dessert
import com.example.pizzadel.pojo.Pizza

class DesssertItemDiffCallback: DiffUtil.ItemCallback<Dessert>() {
    override fun areItemsTheSame(oldItem: Dessert, newItem: Dessert): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dessert, newItem: Dessert): Boolean {
        return oldItem == newItem
    }
}