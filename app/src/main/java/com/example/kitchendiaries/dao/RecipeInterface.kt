package com.example.kitchendiaries.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kitchendiaries.entities.RecipeModel

@Dao
interface RecipeInterface {
    @get:Query("SELECT * FROM Recipies ORDER BY id DESC")
    val allRecepies: List<RecipeModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insterRecipe(recipies:RecipeModel)
}