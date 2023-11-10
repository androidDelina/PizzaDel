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
import com.example.pizzadel.pojo.Dessert
import com.example.pizzadel.pojo.Pizza

class DessertListAdapter():
    ListAdapter<Dessert, DessertListAdapter.DessertItemViewHolder>(DesssertItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item, parent, false)
        return DessertItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DessertItemViewHolder, position: Int) {
        val dessertItem = getItem(position)
        holder.name_tv.text = dessertItem.name
        holder.desc_tv.text = dessertItem.description
        holder.button.text = "From ${dessertItem.price} $"
        Glide.with(holder.itemView.context)
            .load(dessertItem.imageUrl)
            .into(holder.imageView)
    }

    class DessertItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.food_iv)
        val name_tv = view.findViewById<TextView>(R.id.name_tv)
        val desc_tv = view.findViewById<TextView>(R.id.desc_tv)
        val button = view.findViewById<Button>(R.id.button_price)
    }
}