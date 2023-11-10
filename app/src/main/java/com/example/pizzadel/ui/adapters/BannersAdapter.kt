package com.example.pizzadel.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzadel.R

class BannersAdapter(val bannersList: List<Int>):
    RecyclerView.Adapter<BannersAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int = bannersList.count()

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val imageRes = bannersList[position]
        holder.image.setImageResource(imageRes)
    }

    class BannerViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
    }
}