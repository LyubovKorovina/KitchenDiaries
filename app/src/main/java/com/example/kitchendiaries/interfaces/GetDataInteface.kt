package com.example.kitchendiaries.interfaces

import com.example.kitchendiaries.entities.MealCategory
import retrofit2.Call
import retrofit2.http.GET

interface GetDataInteface {
    @GET("categories.php")
    fun getCategoryList(): Call<List<MealCategory>>
}