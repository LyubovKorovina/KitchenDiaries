package com.example.kitchendiaries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.example.kitchendiaries.database.RecipiesDB
import com.example.kitchendiaries.entities.Meal
import com.example.kitchendiaries.entities.MealCategory
import com.example.kitchendiaries.entities.MealsItems
import com.example.kitchendiaries.interfaces.GetDataInteface
import com.example.kitchendiaries.retrofitclient.RFClientInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : MainActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERMISSION = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        readStorageTask()

        val backgroudImage : ImageView = findViewById(R.id.splash_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_slide)
        backgroudImage.startAnimation(sideAnimation)


        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }


fun getMealCategories(){
    val service = RFClientInstance.retrofitInstance!!.create(GetDataInteface::class.java)
    val call = service.getCategoryList()
    call.enqueue(object : Callback<MealCategory> {
        override fun onFailure(call: Call<MealCategory>, t: Throwable) {

            Toast.makeText(this@SplashScreenActivity, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT)
                .show()
        }

        override fun onResponse(
            call: Call<MealCategory>,
            response: Response<MealCategory>
        ) {

            for (arr in response.body()!!.mealcategories!!) {
                getMeal(arr.strcategory)
            }
            insertDataIntoRoomDb(response.body())
        }


    })
}

    fun getMeal(categoryName: String) {
        val service = RFClientInstance.retrofitInstance!!.create(GetDataInteface::class.java)
        val call = service.getMealList(categoryName)
        call.enqueue(object : Callback<Meal> {
            override fun onFailure(call: Call<Meal>, t: Throwable) {

                Toast.makeText(this@SplashScreenActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<Meal>,
                response: Response<Meal>
            ) {

                insertMealDataIntoRoomDb(categoryName, response.body())
            }

        })
    }

    fun insertDataIntoRoomDb(category: MealCategory?) {

        launch {
            this.let {

                for (arr in category!!.mealcategories!!) {
                    RecipiesDB.getDB(this@SplashScreenActivity)
                        .recipeDao().insertCategory(arr)
                }
            }
        }
    }


    fun insertMealDataIntoRoomDb(categoryName: String, meal: Meal?) {

        launch {
            this.let {


                for (arr in meal!!.mealsItem!!) {
                    var mealItemModel = MealsItems(
                        arr.id,
                        arr.idMeal,
                        categoryName,
                        arr.strMeal,
                        arr.strMealThumb
                    )
                    RecipiesDB.getDB(this@SplashScreenActivity)
                        .recipeDao().insertMeal(mealItemModel)
                    Log.d("mealData", arr.toString())
                }
            }
        }
    }

    fun clearDataBase() {
        launch {
            this.let {
                RecipiesDB.getDB(this@SplashScreenActivity).recipeDao().clearDB()
            }
        }
    }

private fun hasReadStoragePermission(): Boolean {
    return EasyPermissions.hasPermissions(this,
        android.Manifest.permission.READ_EXTERNAL_STORAGE)
}

private fun readStorageTask(){
    if (hasReadStoragePermission()){
        clearDataBase()
        getMealCategories()
    }else{
        EasyPermissions.requestPermissions(
            this,
            "App needs storage access",
            READ_STORAGE_PERMISSION,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
}

override fun onRationaleAccepted(requestCode: Int) {

}

override fun onRationaleDenied(requestCode: Int) {

}


override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
        AppSettingsDialog.Builder(this).build().show()
    }
}

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

}



