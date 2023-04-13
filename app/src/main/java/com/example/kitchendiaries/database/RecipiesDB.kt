package com.example.kitchendiaries.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kitchendiaries.dao.RecipeInterface
import com.example.kitchendiaries.entities.*
import com.example.kitchendiaries.entities.converter.MealCatListConverter
import com.example.kitchendiaries.entities.converter.MealListConverter

//Defining database version and entities
@Database(entities = arrayOf(RecipeModel::class, MealCategoryItems::class, MealCategory::class, Meal::class, MealsItems::class), version = 1, exportSchema = false)
@TypeConverters(MealCatListConverter::class, MealListConverter::class)
abstract class RecipiesDB: RoomDatabase(){
    companion object{
        var recipiesDB:RecipiesDB? = null

        @Synchronized
        fun getDB(context: Context): RecipiesDB {
            if (recipiesDB == null) {
                recipiesDB = Room.databaseBuilder(
                    context,
                    RecipiesDB::class.java,
                    "recipe.db"
                ).build()
            }
            return recipiesDB!!
        }
    }

    abstract fun recipeDao():RecipeInterface
}