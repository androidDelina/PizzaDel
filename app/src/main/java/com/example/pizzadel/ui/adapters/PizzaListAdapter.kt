package com.example.pizzadel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pizzadel.R
import com.example.pizzadel.pojo.Pizza

class PizzaListAdapter(): ListAdapter<Pizza, PizzaListAdapter.PizzaItemViewHolder>(PizzaItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)
        return PizzaItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {

        val pizzaItem = getItem(position)
        holder.name_tv.text = pizzaItem.name
        holder.desc_tv.text = pizzaItem.description
        holder.button.text = "From ${pizzaItem.price} $"
        Glide.with(holder.itemView.context)
            .load(pizzaItem.imageUrl)
            .into(holder.imageView)
    }

    class PizzaItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.food_iv)
        val name_tv = view.findViewById<TextView>(R.id.name_tv)
        val desc_tv = view.findViewById<TextView>(R.id.desc_tv)
        val button = view.findViewById<Button>(R.id.button_price)
    }
}