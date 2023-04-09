package com.example.kitchendiaries.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kitchendiaries.dao.RecipeInterface
import com.example.kitchendiaries.entities.RecipeModel
import java.security.AccessControlContext

//Defining database version and entities
@Database(entities = [RecipeModel::class], version = 1, exportSchema = false)
abstract class RecipiesDB: RoomDatabase(){
    companion object{
        var recipiesDB:RecipiesDB? = null

        @Synchronized
        fun getDB(context: Context): RecipiesDB{
            if (recipiesDB !=null ) {
                recipiesDB = Room.databaseBuilder(
                    context,
                    RecipiesDB::class.java,
                    name = "recipe.db"
                ).build()
            }
            return recipiesDB!!
        }
    }

    abstract fun recipeDao():RecipeInterface
}