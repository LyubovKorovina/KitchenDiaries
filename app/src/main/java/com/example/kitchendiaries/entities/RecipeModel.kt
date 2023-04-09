package com.example.kitchendiaries.entities
import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "Recipies")
data class RecipeModel (
    @PrimaryKey(autoGenerate = true)
    var id:Int
    ) : java.io.Serializable