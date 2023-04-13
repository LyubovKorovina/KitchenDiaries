package com.example.kitchendiaries.interfaces

import com.codingwithme.recipeapp.entities.MealResponse
import com.example.kitchendiaries.entities.Meal
import com.example.kitchendiaries.entities.MealCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataInteface {
    @GET("categories.php")
    fun getCategoryList(): Call<MealCategory>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>

}