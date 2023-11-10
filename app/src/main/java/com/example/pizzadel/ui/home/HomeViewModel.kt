package com.example.pizzadel.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pizzadel.R
import com.example.pizzadel.api.ApiFactory
import com.example.pizzadel.database.AppDatabase
import com.example.pizzadel.pojo.Category
import com.example.pizzadel.pojo.Dessert
import com.example.pizzadel.pojo.Pizza
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val compositeDisposable = CompositeDisposable()
    private val database = AppDatabase.getInstance(application)

    private val _citiesArray = MutableLiveData<Array<String>>().apply {
        value = arrayOf("Moscow", "Volgograd", "Vologda")
    }
    val citiesArray: LiveData<Array<String>> = _citiesArray

    private val _bannersImgRes = MutableLiveData<List<Int>>().apply {
        value = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4)
    }
    val bannersImgRes: LiveData<List<Int>> = _bannersImgRes

    private val _categoriesList = MutableLiveData<MutableList<Category>>().apply {
        value = mutableListOf(
            Category(0, "Pizza", true),
            Category(1, "Desserts", false))
    }
    val categoriesList: LiveData<MutableList<Category>> = _categoriesList

    fun loadPizzaData() {
        val disposable = ApiFactory.apiService.getPizzaList()
            .subscribeOn(Schedulers.io())
            .subscribe({
                database.productsDao().insertNewPizzaInfo(it)
            }, {
                Log.e("PIZZA_ERROR", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun loadDessertData() {
        val disposable = ApiFactory.apiService.getDessertsList()
            .subscribeOn(Schedulers.io())
            .subscribe({
                database.productsDao().insertNewDessertsInfo(it)
            }, {
                Log.e("DESSERT_ERROR", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun ternOnOffCategory(category: Category) {
        if (category.isTernOn) {
            return
        }
        val newCategory = category.copy(isTernOn = category.isTernOn.not())
        val newListOfCategory = _categoriesList.value
        newListOfCategory?.let {

            for (category in it) {
                category.isTernOn = false
            }

            it.removeAt(category.id)
            it.add(category.id, newCategory)
        }
        _categoriesList.value = newListOfCategory
    }

    fun getPizzaDataFromDb(): LiveData<List<Pizza>> {
        return database.productsDao().getAllPizza()
    }

    fun getDessertDataFromDb(): LiveData<List<Dessert>> {
        return database.productsDao().getAllDeserts()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}