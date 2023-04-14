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
import com.example.kitchendiaries.entities.MealsItems
import com.example.kitchendiaries.entities.RecipeModel
import kotlinx.coroutines.launch

class HomeActivity : MainActivity() {

    //Here we are initializing Adapter and Category array lists
    var mainCatArray = ArrayList<MealCategoryItems>()
    var subCatArray = ArrayList<MealsItems>()
    var mainCatAdapter = MainCatAdapter()
    var subCatAdapter = SubCatAdapter()

    // Declare as lateinit
    private lateinit var rv_mainCategory: RecyclerView
    private lateinit var rv_subCategory: RecyclerView
    private lateinit var tvCategory: TextView // Added TextView for tvCategory

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

        mainCatAdapter.setClickListener(onClicked)

        // Initialize rv_mainCategory, rv_subCategory, and tvCategory
        rv_mainCategory = findViewById(R.id.rv_mainCategory)
        rv_subCategory = findViewById(R.id.rv_subCategory)
        tvCategory = findViewById(R.id.tvCategory)

    }

    private val onClicked = object : MainCatAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String){
            getMealDataFromDb(categoryName)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipiesDB.getDB(this@HomeActivity).recipeDao().getAllCategories()
                mainCatArray = cat as ArrayList<MealCategoryItems>
                mainCatArray.reverse()
                mainCatAdapter.setData(mainCatArray)
                //Here we are setting an adapter to RecycleView

                getMealDataFromDb(mainCatArray[0].strcategory)
                rv_mainCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, true)
                rv_mainCategory.adapter = mainCatAdapter
            }
        }
    }

    private fun getMealDataFromDb(categoryName:String){
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipiesDB.getDB(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                subCatArray = cat as ArrayList<MealsItems>
                subCatAdapter.setData(subCatArray)
                //Here we are setting an adapter to RecycleView
                rv_subCategory.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, true)
                rv_subCategory.adapter = subCatAdapter
            }
        }
    }
}