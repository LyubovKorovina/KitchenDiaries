package com.example.kitchendiaries.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kitchendiaries.entities.MealCategoryItems
import com.example.kitchendiaries.entities.MealsItems


@Dao
interface RecipeInterface {

    @Query("SELECT * FROM MealCategoryItems ORDER BY id DESC")
    suspend fun getAllCategories() : List<MealCategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: MealCategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItems: MealsItems?)

    //this is a DELETE query to clear data on app startup
    @Query("DELETE from MealCategory")
    suspend fun clearDB()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    suspend fun getSpecificMealList(categoryName:String) : List<MealsItems>
}