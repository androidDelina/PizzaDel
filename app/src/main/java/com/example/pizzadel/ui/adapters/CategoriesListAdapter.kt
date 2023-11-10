package com.example.pizzadel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzadel.R
import com.example.pizzadel.pojo.Category

class CategoriesListAdapter():
    ListAdapter<Category, CategoriesListAdapter.CategoriesItemViewHolder>(CategoryDiffCallback()) {

    var onCategoryClickListener: OnCategoryClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesItemViewHolder {
        val layoutRes = when (viewType) {
            ITEM_VIEW_TYPE_1 -> R.layout.category_passive
            ITEM_VIEW_TYPE_2 -> R.layout.category_active
            else -> R.layout.category_passive
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return CategoriesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesItemViewHolder, position: Int) {
        val categoryItem = getItem(position)
        holder.textView.text = categoryItem.text
        onCategoryClickListener?.let { onCategoryClickListener ->
            holder.itemView.setOnClickListener {
                onCategoryClickListener.onCategoryClick(categoryItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isTernOn) {
            ITEM_VIEW_TYPE_2
        } else {
            ITEM_VIEW_TYPE_1
        }
    }

    class CategoriesItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
    }

    companion object {
        const val ITEM_VIEW_TYPE_1 = 0
        const val ITEM_VIEW_TYPE_2 = 1
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(category: Category)
    }
}