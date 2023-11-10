package com.example.pizzadel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pizzadel.pojo.Dessert
import com.example.pizzadel.pojo.Pizza
import io.reactivex.Single

@Dao
interface ProductsDao {

    @Query("SELECT * FROM pizza_list")
    fun getAllPizza(): LiveData<List<Pizza>>

    @Query("SELECT * FROM desserts_list")
    fun getAllDeserts(): LiveData<List<Dessert>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPizzaInfo(pizzaList: List<Pizza>)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertNewDessertsInfo(dessertsList: List<Dessert>)
}