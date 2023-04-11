package com.example.kitchendiaries.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealCategory")

data class MealCategory(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "mealcategories")
    val mealcategories: List<MealCategoryItems>
)