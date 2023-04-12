package com.example.kitchendiaries.entities

import androidx.room.*
import com.example.kitchendiaries.entities.converter.MealCatListConverter

@Entity(tableName = "MealCategory")

data class MealCategory(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "mealcategories")
    //Type converters annotation
    @TypeConverters(MealCatListConverter::class)
    val mealcategories: List<MealCategoryItems>? = null
)