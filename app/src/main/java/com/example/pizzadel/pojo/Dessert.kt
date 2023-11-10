package com.example.pizzadel.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity (tableName = "desserts_list")
data class Dessert(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("price")
    @Expose
    val price: Int,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("img")
    @Expose
    val imageUrl: String
)
