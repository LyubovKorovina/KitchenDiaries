package com.example.kitchendiaries.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kitchendiaries.entities.MealCategory
import com.example.kitchendiaries.entities.RecipeModel

@Dao
interface RecipeInterface {
    @get:Query("SELECT * FROM MealCategory ORDER BY id DESC")
    val getAllCategories: List<MealCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: MealCategory)
}