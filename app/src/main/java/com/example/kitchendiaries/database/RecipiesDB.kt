package com.example.kitchendiaries.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kitchendiaries.dao.RecipeInterface
import com.example.kitchendiaries.entities.MealCategoryItems
import com.example.kitchendiaries.entities.MealCategory
import com.example.kitchendiaries.entities.RecipeModel
import com.example.kitchendiaries.entities.converter.MealCatListConverter

//Defining database version and entities
@Database(entities = arrayOf(RecipeModel::class, MealCategoryItems::class, MealCategory::class), version = 1, exportSchema = false)
@TypeConverters(MealCatListConverter::class)
abstract class RecipiesDB: RoomDatabase(){
    companion object{
        private var recipiesDB:RecipiesDB? = null

        @Synchronized
        fun getDB(context: Context): RecipiesDB {
            if (recipiesDB == null) {
                recipiesDB = Room.databaseBuilder(
                    context.applicationContext,
                    RecipiesDB::class.java,
                    "recipe.db"
                ).build()
            }
            return recipiesDB!!
        }
    }

    abstract fun recipeDao():RecipeInterface
}