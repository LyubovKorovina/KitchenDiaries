package com.example.kitchendiaries.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealCategoryItems")

data class MealCategoryItems(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "idCategory")
    val idCategory: String,

    @ColumnInfo(name = "strCategory")
    val strCategory: String,

    @ColumnInfo(name = "strCategoryDescription")
    val strCategoryDescription: String,

    @ColumnInfo(name = "strCategoryThumb")
    val strCategoryThumb: String
)