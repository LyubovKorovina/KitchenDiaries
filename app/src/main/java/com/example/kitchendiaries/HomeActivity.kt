package com.example.kitchendiaries

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kitchendiaries.adapters.MainCatAdapter
import com.example.kitchendiaries.adapters.SubCatAdapter
import com.example.kitchendiaries.database.RecipiesDB
import com.example.kitchendiaries.entities.MealCategoryItems
import com.example.kitchendiaries.entities.RecipeModel
import kotlinx.coroutines.launch


class HomeActivity : MainActivity() {

    //Here we are initializing Adapter and Category array lists
    var mainCatArray = ArrayList<MealCategoryItems>()
    var subCatArray = ArrayList<RecipeModel>()
    var mainCatAdapter = MainCatAdapter()
    var subCatAdapter = SubCatAdapter()

    // Declare as lateinit
    private lateinit var rv_mainCategory: RecyclerView
    private lateinit var rv_subCategory: RecyclerView

    //private lateinit var rv_mainCategory: RecyclerView
    // Initialize rv_mainCategory directly without using findViewById()
    // Initialize rv_mainCategory using findViewById()
    //val rv_mainCategory: RecyclerView = findViewById(R.id.rv_mainCategory)
    // Initialize rv_subCategory using findViewById()
    //val rv_subCategory: RecyclerView = findViewById(R.id.rv_subCategory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()

        // Initialize rv_mainCategory and rv_subCategory
        rv_mainCategory = findViewById(R.id.rv_mainCategory)
        rv_subCategory = findViewById(R.id.rv_subCategory)


        subCatArray.add(RecipeModel(1, "Carrot pie"))
        subCatArray.add(RecipeModel(2, "Fried Chicken"))
        subCatArray.add(RecipeModel(3, "Banana pancake"))
        subCatArray.add(RecipeModel(4, "Lemonade"))

        subCatAdapter.setData(subCatArray)



        // Set subCatAdapter to rv_subCategory (assuming you have another RecyclerView named rv_subCategory)
        rv_subCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        rv_subCategory.adapter = subCatAdapter
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipiesDB.getDB(this@HomeActivity).recipeDao().getAllCategories()
                mainCatArray = cat as ArrayList<MealCategoryItems>
                mainCatArray.reverse()

                mainCatAdapter.setData(mainCatArray)
                //Here we are setting an adapter to RecycleView
                rv_mainCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, true)
                rv_mainCategory.adapter = mainCatAdapter
            }


        }
    }
}