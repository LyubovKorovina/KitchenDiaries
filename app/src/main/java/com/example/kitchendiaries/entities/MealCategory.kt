package com.example.kitchendiaries.entities

import androidx.room.*
import com.example.kitchendiaries.entities.converter.MealCatListConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MealCategory")

data class MealCategory(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "categoryItems")
    //Type converters annotation
    @Expose
    @SerializedName("categories")
    @TypeConverters(MealCatListConverter::class)
    val mealcategories: List<MealCategoryItems>? = null
)