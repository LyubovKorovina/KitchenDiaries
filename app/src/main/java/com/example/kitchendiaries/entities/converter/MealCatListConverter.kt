package com.example.kitchendiaries.entities.converter

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.kitchendiaries.entities.MealCategory
import com.example.kitchendiaries.entities.MealCategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MealCatListConverter {
    @TypeConverter
    fun fromMealCatList(mealcategory: List<MealCategoryItems>?): String?{
        if (mealcategory == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<List<MealCategoryItems>>() {}.type // Use List<MealCategory> instead of just MealCategory
            return gson.toJson(mealcategory, type)
        }
    }

    @TypeConverter
    fun toMealCatList(mealcategoryString: String): List<MealCategoryItems>? {
        if (mealcategoryString == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<List<MealCategoryItems>>() {

            }.type // Use List<MealCategory> instead of just MealCategory
            return gson.fromJson(mealcategoryString, type)
        }
    }
}