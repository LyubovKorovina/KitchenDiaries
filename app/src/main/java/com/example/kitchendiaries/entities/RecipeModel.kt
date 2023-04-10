package com.example.kitchendiaries.entities
import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "Recipies")
data class RecipeModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "mealName")
    val mealName:String

    ) : java.io.Serializable