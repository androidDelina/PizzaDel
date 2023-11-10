package com.example.pizzadel.api

import com.example.pizzadel.pojo.Dessert
import com.example.pizzadel.pojo.Pizza
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("pizzas")
    fun getPizzaList(): Single<List<Pizza>>

    @GET("desserts")
    fun getDessertsList(): Single<List<Dessert>>

}